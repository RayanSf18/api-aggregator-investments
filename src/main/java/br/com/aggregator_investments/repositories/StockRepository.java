package br.com.aggregator_investments.repositories;

import br.com.aggregator_investments.domain.account.Account;
import br.com.aggregator_investments.domain.stock.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StockRepository extends JpaRepository<Stock, String> {

}