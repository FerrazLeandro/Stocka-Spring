package br.com.stocka.stockaspring.service.stock;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.stocka.stockaspring.model.StockModel;
import br.com.stocka.stockaspring.repository.StockRepository;
import jakarta.transaction.Transactional;

@Service
public class StockServiceImpl implements StockService {

    final StockRepository stockRepository;

    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Transactional
    @Override
    public Long save(StockModel stockModel) {
        StockModel stockCreated = stockRepository.save(stockModel);        
        return stockCreated.getStockId();
    }

    @Override
    public boolean existsByDescription(String description) {
        return stockRepository.existsByDescription(description);
    }
    
    @Override
    public List<StockModel> findAll() {
        return stockRepository.findAll();
    }

    @Override
    public Optional<StockModel> findById(Long id) {
        return stockRepository.findById(id);
    }

    @Transactional
    @Override
    public void delete(StockModel stockModel) {
        stockRepository.delete(stockModel);
    }

    @Override
    public Optional<StockModel> findByDescription(String description) {
        return stockRepository.findByDescription(description);
    } 
    
}
