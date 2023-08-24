package br.com.stocka.stockaspring.service.stock;

import java.util.List;
import java.util.Optional;

import br.com.stocka.stockaspring.model.StockModel;

public interface StockService {
    public Long save(StockModel stockModel);
    public boolean existsByDescription(String description);
    public Optional<StockModel> findByDescription(String description);    
    public List<StockModel> findAll();
    public Optional<StockModel> findById(Long id);    
    public void delete(StockModel stockModel);  
}
