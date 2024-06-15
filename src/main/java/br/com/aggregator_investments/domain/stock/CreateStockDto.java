package br.com.aggregator_investments.domain.stock;

public record CreateStockDto(String stockId, String description) {

                    public Stock toStock() {
                                        return new Stock(stockId, description);
                    }
}
