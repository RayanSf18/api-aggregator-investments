package br.com.aggregator_investments.domain.account;

import br.com.aggregator_investments.domain.accountstock.AccountStock;
import br.com.aggregator_investments.domain.billingaddress.BillingAddress;
import br.com.aggregator_investments.domain.user.User;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_account")
public class Account implements Serializable {

                    @Id
                    @GeneratedValue(strategy = GenerationType.UUID)
                    @Column(name = "account_id")
                    private UUID accountId;

                    @Column(nullable = false)
                    private String description;

                    @ManyToOne
                    @JoinColumn(name = "user_id", nullable = false)
                    private User user;

                    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
                    @PrimaryKeyJoinColumn
                    private BillingAddress billingAddress;

                    @OneToMany(mappedBy = "account")
                    private List<AccountStock> accountStocks = new ArrayList<>();

                    public Account() {

                    }

                    public Account(String description, User user, BillingAddress billingAddress) {
                                        this.description = description;
                                        this.user = user;
                                        this.billingAddress = billingAddress;
                    }

                    public UUID getAccountId() {
                                        return accountId;
                    }

                    public void setAccountId(UUID accountId) {
                                        this.accountId = accountId;
                    }

                    public String getDescription() {
                                        return description;
                    }

                    public void setDescription(String description) {
                                        this.description = description;
                    }

                    public User getUser() {
                                        return user;
                    }

                    public void setUser(User user) {
                                        this.user = user;
                    }

                    public BillingAddress getBillingAddress() {
                                        return billingAddress;
                    }

                    public void setBillingAddress(BillingAddress billingAddress) {
                                        this.billingAddress = billingAddress;
                    }

                    public List<AccountStock> getAccountStocks() {
                                        return accountStocks;
                    }

                    public void setAccountStocks(List<AccountStock> accountStocks) {
                                        this.accountStocks = accountStocks;
                    }
}
