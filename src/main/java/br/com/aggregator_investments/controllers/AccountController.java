package br.com.aggregator_investments.controllers;

import br.com.aggregator_investments.domain.account.AccountStockResponseDto;
import br.com.aggregator_investments.domain.account.AssociateAccountStockDto;
import br.com.aggregator_investments.services.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(value = "/{accountId}/stocks")
    public ResponseEntity<Void> associateStock(@PathVariable(name = "accountId") String accountId, @RequestBody AssociateAccountStockDto associateAccountStockDto) {
        this.accountService.associateStockWithAccount(accountId, associateAccountStockDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{accountId}/stocks")
    public ResponseEntity<List<AccountStockResponseDto>> associateStock(@PathVariable(name = "accountId") String accountId) {
        var listOfStocks = this.accountService.getStockListFromDatabaseAccount(accountId);
        return ResponseEntity.ok().body(listOfStocks);
    }
}
