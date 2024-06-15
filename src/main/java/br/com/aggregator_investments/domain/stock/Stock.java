package br.com.aggregator_investments.domain.stock;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "tb_stock")
public class Stock implements Serializable {

                    @Id
                    @Column(name = "stock_id", nullable = false, unique = true)
                    private String stockId;

                    @Column(nullable = false, length = 500)
                    private String description;

                    public Stock() {

                    }

                    public Stock(String stockId, String description) {
                                        this.stockId = stockId;
                                        this.description = description;
                    }

                    public String getStockId() {
                                        return stockId;
                    }

                    public void setStockId(String stockId) {
                                        this.stockId = stockId;
                    }

                    public String getDescription() {
                                        return description;
                    }

                    public void setDescription(String description) {
                                        this.description = description;
                    }
}
