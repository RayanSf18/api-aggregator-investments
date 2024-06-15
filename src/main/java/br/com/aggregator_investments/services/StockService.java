package br.com.aggregator_investments.services;

import br.com.aggregator_investments.domain.stock.CreateStockDto;
import br.com.aggregator_investments.repositories.StockRepository;
import org.springframework.stereotype.Service;

@Service
public class StockService {

                    private final StockRepository stockRepository;

                    public StockService(StockRepository stockRepository) {
                                        this.stockRepository = stockRepository;
                    }

                    public void createNewStockInDatabase(CreateStockDto stockDto) {
                                        var newStock = stockDto.toStock();
                                        this.stockRepository.save(newStock);
                    }
}
