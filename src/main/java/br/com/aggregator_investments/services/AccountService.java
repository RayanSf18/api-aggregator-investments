package br.com.aggregator_investments.services;

import br.com.aggregator_investments.client.BrapiClient;
import br.com.aggregator_investments.domain.account.AccountStockResponseDto;
import br.com.aggregator_investments.domain.account.AssociateAccountStockDto;
import br.com.aggregator_investments.domain.accountstock.AccountStock;
import br.com.aggregator_investments.domain.accountstock.AccountStockId;
import br.com.aggregator_investments.repositories.AccountRepository;
import br.com.aggregator_investments.repositories.AccountStockRepository;
import br.com.aggregator_investments.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    @Value("#{environment.TOKEN}")
    private String TOKEN;
    private final AccountRepository accountRepository;
    private final StockRepository stockRepository;
    private final AccountStockRepository accountStockRepository;
    private final BrapiClient brapiClient;

    public AccountService(AccountRepository accountRepository, StockRepository stockRepository, AccountStockRepository accountStockRepository, BrapiClient brapiClient) {
        this.accountRepository = accountRepository;
        this.stockRepository = stockRepository;
        this.accountStockRepository = accountStockRepository;
        this.brapiClient = brapiClient;
    }


    public void associateStockWithAccount(String accountId, AssociateAccountStockDto associateAccountStockDto) {
        var savedAccount = this.accountRepository.findById(UUID.fromString(accountId)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        var savedStock = this.stockRepository.findById(associateAccountStockDto.stockId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        var accountStockId = new AccountStockId(savedAccount.getAccountId(), savedStock.getStockId().toString());
        var newAccountStock = new AccountStock(accountStockId, savedAccount, savedStock, associateAccountStockDto.quantity());
        this.accountStockRepository.save(newAccountStock);
    }

    public List<AccountStockResponseDto> getStockListFromDatabaseAccount(String accountId) {
        var savedAccount = this.accountRepository.findById(UUID.fromString(accountId)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return savedAccount.getAccountStocks()
                .stream()
                .map(accountStock -> new AccountStockResponseDto(
                        accountStock.getStock().getStockId(),
                        accountStock.getQuantity(),
                        getTotal(accountStock.getQuantity(), accountStock.getStock().getStockId())
                ))
                .toList();
    }

    private double getTotal(Integer quantity, String stockId) {
        var response = brapiClient.getQuote(TOKEN, stockId);
        var price = response.results().getFirst().regularMarketPrice();
        return quantity * price;
    }
}
