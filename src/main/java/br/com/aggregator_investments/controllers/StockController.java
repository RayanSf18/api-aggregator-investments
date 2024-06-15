package br.com.aggregator_investments.controllers;

import br.com.aggregator_investments.domain.stock.CreateStockDto;
import br.com.aggregator_investments.domain.user.CreateUserDto;
import br.com.aggregator_investments.services.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping(value = "/v1/stocks")
public class StockController {

                    private final StockService stockService;

                    public StockController(StockService stockService) {
                                        this.stockService = stockService;
                    }

                    @PostMapping
                    public ResponseEntity<Void> createNewStock(@RequestBody CreateStockDto stockDto) {
                                        this.stockService.createNewStockInDatabase(stockDto);
                                        return ResponseEntity.ok().build();
                    }
}
