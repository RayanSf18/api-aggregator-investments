package br.com.aggregator_investments.controllers;

import br.com.aggregator_investments.domain.account.AccountResponseDto;
import br.com.aggregator_investments.domain.account.CreateAccountDto;
import br.com.aggregator_investments.domain.user.CreateUserDto;
import br.com.aggregator_investments.domain.user.UpdateUserDto;
import br.com.aggregator_investments.domain.user.User;
import br.com.aggregator_investments.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Void> createNewUser(@RequestBody CreateUserDto userDto) {
        var userId = this.userService.createNewUserInDatabase(userDto);
        return ResponseEntity.created(URI.create("/v1/users/" + userId.toString())).build();
    }

    @PostMapping(value = "/{userId}/accounts")
    public ResponseEntity<Void> createNewAccount(@PathVariable(name = "userId") String userId, @RequestBody CreateAccountDto accountDto) {
        this.userService.createNewAccountInDatabase(userId, accountDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable(name = "userId") String userId) {
        var userFromDb = this.userService.getUserByIdFromDatabase(userId);
        if (userFromDb.isEmpty()) ResponseEntity.noContent().build();
        return ResponseEntity.ok().body(userFromDb.get());
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        var listOfUsers = this.userService.getListOfUsers();
        return ResponseEntity.ok().body(listOfUsers);
    }

    @GetMapping(value = "/{userId}/accounts")
    public ResponseEntity<List<AccountResponseDto>> getAllAccountsOfUser(@PathVariable(name = "userId") String userId) {
        var listOfAccountsResponse = this.userService.getListOfAccountsResponse(userId);
        return ResponseEntity.ok().body(listOfAccountsResponse);
    }

    @PutMapping(value = "/{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable(name = "userId") String userId, @RequestBody UpdateUserDto userDto) {
        this.userService.updateExistingDatabaseUser(userId, userDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable(name = "userId") String userId) {
        this.userService.deleteUserByIdFromDatabase(userId);
        return ResponseEntity.noContent().build();
    }
}
