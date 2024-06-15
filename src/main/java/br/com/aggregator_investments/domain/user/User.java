package br.com.aggregator_investments.domain.user;

import br.com.aggregator_investments.domain.account.Account;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_user")
public class User implements Serializable {

                    @Id
                    @GeneratedValue(strategy = GenerationType.UUID)
                    private UUID id;

                    @Column(nullable = false, length = 50)
                    private String username;

                    @Column(nullable = false, unique = true)
                    private String email;

                    @Column(nullable = false)
                    private String password;

                    @CreationTimestamp
                    @Column(name = "created_at")
                    private Instant createdAt;

                    @UpdateTimestamp
                    @Column(name = "updated_at", updatable = false)
                    private Instant updatedAt;

                    @OneToMany(mappedBy = "user")
                    private List<Account> accounts = new ArrayList<>();

                    public User() {

                    }

                    public User(UUID id, String username, String email, String password, Instant createdAt, Instant updatedAt) {
                                        this.id = id;
                                        this.username = username;
                                        this.email = email;
                                        this.password = password;
                                        this.createdAt = createdAt;
                                        this.updatedAt = updatedAt;
                    }

                    public UUID getId() {
                                        return id;
                    }

                    public void setId(UUID id) {
                                        this.id = id;
                    }

                    public String getUsername() {
                                        return username;
                    }

                    public void setUsername(String username) {
                                        this.username = username;
                    }

                    public String getEmail() {
                                        return email;
                    }

                    public void setEmail(String email) {
                                        this.email = email;
                    }

                    public String getPassword() {
                                        return password;
                    }

                    public void setPassword(String password) {
                                        this.password = password;
                    }

                    public Instant getCreatedAt() {
                                        return createdAt;
                    }

                    public void setCreatedAt(Instant createdAt) {
                                        this.createdAt = createdAt;
                    }

                    public Instant getUpdatedAt() {
                                        return updatedAt;
                    }

                    public void setUpdatedAt(Instant updatedAt) {
                                        this.updatedAt = updatedAt;
                    }

                    public List<Account> getAccounts() {
                                        return accounts;
                    }

                    public void setAccounts(List<Account> accounts) {
                                        this.accounts = accounts;
                    }
}
