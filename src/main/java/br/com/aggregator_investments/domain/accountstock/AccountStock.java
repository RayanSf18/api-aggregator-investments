package br.com.aggregator_investments.domain.accountstock;

import br.com.aggregator_investments.domain.account.Account;
import br.com.aggregator_investments.domain.stock.Stock;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "tb_account_stock")
public class AccountStock implements Serializable {

                    @EmbeddedId
                    private AccountStockId id;

                    @ManyToOne
                    @MapsId("accountId")
                    @JoinColumn(name = "account_id", nullable = false)
                    private Account account;

                    @ManyToOne
                    @MapsId("stockId")
                    @JoinColumn(name = "stock_id", nullable = false)
                    private Stock stock;

                    @Column(name = "quantity")
                    private Integer quantity;

                    public AccountStock() {

                    }

                    public AccountStock(AccountStockId id, Account account, Stock stock, Integer quantity) {
                                        this.id = id;
                                        this.account = account;
                                        this.stock = stock;
                                        this.quantity = quantity;
                    }

                    public AccountStockId getId() {
                                        return id;
                    }

                    public void setId(AccountStockId id) {
                                        this.id = id;
                    }

                    public Account getAccount() {
                                        return account;
                    }

                    public void setAccount(Account account) {
                                        this.account = account;
                    }

                    public Stock getStock() {
                                        return stock;
                    }

                    public void setStock(Stock stock) {
                                        this.stock = stock;
                    }

                    public Integer getQuantity() {
                                        return quantity;
                    }

                    public void setQuantity(Integer quantity) {
                                        this.quantity = quantity;
                    }
}
