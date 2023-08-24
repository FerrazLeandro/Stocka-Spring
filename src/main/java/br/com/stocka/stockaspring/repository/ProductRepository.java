package br.com.stocka.stockaspring.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.stocka.stockaspring.model.ProductModel;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {
    Optional<ProductModel> findByName(String name);
    Boolean existsByName(String name);
    Optional<ProductModel> findByBarCode(String barCode);
    Boolean existsByBarCode(String barCode);

    List<ProductModel> findByExpirationDateLessThanEqual(LocalDate fromDate);
}
