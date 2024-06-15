package br.com.aggregator_investments.services;

import br.com.aggregator_investments.domain.account.Account;
import br.com.aggregator_investments.domain.account.AccountResponseDto;
import br.com.aggregator_investments.domain.account.CreateAccountDto;
import br.com.aggregator_investments.domain.billingaddress.BillingAddress;
import br.com.aggregator_investments.domain.user.CreateUserDto;
import br.com.aggregator_investments.domain.user.UpdateUserDto;
import br.com.aggregator_investments.domain.user.User;
import br.com.aggregator_investments.repositories.AccountRepository;
import br.com.aggregator_investments.repositories.BillingAddressRepository;
import br.com.aggregator_investments.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

                    private final UserRepository userRepository;
                    private final AccountRepository accountRepository;
                    private final BillingAddressRepository billingAddressRepository;

                    public UserService(UserRepository userRepository, AccountRepository accountRepository, BillingAddressRepository billingAddressRepository) {
                                        this.userRepository = userRepository;
                                        this.accountRepository = accountRepository;
                                        this.billingAddressRepository = billingAddressRepository;
                    }


                    public UUID createNewUserInDatabase(CreateUserDto userDto) {
                                        var newUser = userDto.convertToUser();
                                        var savedUser = this.userRepository.save(newUser);
                                        return savedUser.getId();
                    }

                    public Optional<User> getUserByIdFromDatabase(String userId) {
                                        return this.userRepository.findById(UUID.fromString(userId));
                    }


                    public List<User> getListOfUsers() {
                                        return this.userRepository.findAll();
                    }

                    public void deleteUserByIdFromDatabase(String userId) {
                                        var uuid = UUID.fromString(userId);
                                        var userExistsInDb = this.userRepository.existsById(uuid);
                                        if (userExistsInDb) this.userRepository.deleteById(uuid);
                    }

                    public void updateExistingDatabaseUser(String userId, UpdateUserDto userDto) {
                                        var savedUser = this.getUserByIdFromDatabase(userId);
                                        this.updateUser(savedUser.get(), userDto);
                                        this.userRepository.save(savedUser.get());
                    }

                    public void createNewAccountInDatabase(String userId, CreateAccountDto accountDto) {
                                        var savedUser = this.getUserByIdFromDatabase(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
                                        var newAccount = new Account(accountDto.description(), savedUser, null);
                                        var savedAccount = this.accountRepository.save(newAccount);
                                        var newBillingAddress = new BillingAddress(savedAccount.getAccountId(), accountDto.street(), accountDto.number(), newAccount);
                                        this.billingAddressRepository.save(newBillingAddress);
                    }

                    // Auxiliary methods

                    private void updateUser(User savedUser, UpdateUserDto userDto) {
                                        if (userDto.username() != null) savedUser.setUsername(userDto.username());
                                        if (userDto.password() != null) savedUser.setPassword(userDto.password());
                    }


                    public List<AccountResponseDto> getListOfAccountsResponse(String userId) {
                                        var savedUser = this.getUserByIdFromDatabase(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
                                        return savedUser.getAccounts().stream().map(account -> AccountResponseDto.toAccountResponse(account)).toList();
                    }

}
