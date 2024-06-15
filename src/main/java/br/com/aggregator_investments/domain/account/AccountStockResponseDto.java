package br.com.aggregator_investments.domain.account;

import br.com.aggregator_investments.domain.accountstock.AccountStock;

public record AccountStockResponseDto(String stockId, int quantity, double total) {

                    public static AccountStockResponseDto toAccountStockResponseDto(AccountStock accountStock) {
                                        return new AccountStockResponseDto(accountStock.getStock().getStockId(), accountStock.getQuantity(),0.0);
                    }
}
