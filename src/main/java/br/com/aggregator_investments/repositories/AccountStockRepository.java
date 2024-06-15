package br.com.aggregator_investments.repositories;

import br.com.aggregator_investments.domain.accountstock.AccountStock;
import br.com.aggregator_investments.domain.accountstock.AccountStockId;
import br.com.aggregator_investments.domain.stock.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountStockRepository extends JpaRepository<AccountStock, AccountStockId> {

}
