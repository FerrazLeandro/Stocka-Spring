package br.com.stocka.stockaspring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.stocka.stockaspring.model.StockModel;

public interface StockRepository extends JpaRepository<StockModel, Long> {
    Optional<StockModel> findByDescription(String description);    
    Boolean existsByDescription(String description);
}
