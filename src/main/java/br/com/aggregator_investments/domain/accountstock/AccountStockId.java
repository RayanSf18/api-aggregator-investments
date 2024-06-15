package br.com.aggregator_investments.domain.accountstock;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class AccountStockId {

                    @Column(name = "account_id", nullable = false)
                    private UUID accountId;

                    @Column(name = "stock_id", nullable = false)
                    private String stockId;

                    public AccountStockId() {

                    }

                    public AccountStockId(UUID accountId, String stockId) {
                                        this.accountId = accountId;
                                        this.stockId = stockId;
                    }

                    public UUID getAccountId() {
                                        return accountId;
                    }

                    public void setAccountId(UUID accountId) {
                                        this.accountId = accountId;
                    }

                    public String getStockId() {
                                        return stockId;
                    }

                    public void setStockId(String stockId) {
                                        this.stockId = stockId;
                    }
}
