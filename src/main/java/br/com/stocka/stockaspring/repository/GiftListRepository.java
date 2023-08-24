package br.com.stocka.stockaspring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.stocka.stockaspring.model.GiftListModel;

public interface GiftListRepository extends JpaRepository<GiftListModel, Long> {
    Optional<GiftListModel> findByDescription(String description);    
    Boolean existsByDescription(String description);
}
