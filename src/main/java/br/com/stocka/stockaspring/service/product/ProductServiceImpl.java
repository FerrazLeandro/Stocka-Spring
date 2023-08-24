package br.com.stocka.stockaspring.service.product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import br.com.stocka.stockaspring.dto.ProductDto;
import br.com.stocka.stockaspring.model.ProductModel;
import br.com.stocka.stockaspring.repository.ProductRepository;
import jakarta.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

    final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    @Override
    public Long save(ProductModel ProductModel) {
        ProductModel productCreated = productRepository.save(ProductModel);
        return productCreated.getProductId();
    }

    @Transactional
    @Override
    public List<ProductModel> saveAll(List<ProductDto> products) {
        List<ProductModel> productModels = new ArrayList<>();

        for (ProductDto productDto : products) {
            ProductModel productModel = new ProductModel();
            BeanUtils.copyProperties(productDto, productModel);
            productModels.add(productModel);
        }

        return productRepository.saveAll(productModels);
    }

    @Override
    public List<ProductModel> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<ProductModel> findById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional
    @Override
    public void delete(ProductModel ProductModel) {
        productRepository.delete(ProductModel);
    }

    @Override
    public boolean existsByName(String name) {
        return productRepository.existsByName(name);
    }

    @Override
    public Optional<ProductModel> findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public boolean existsByBarCode(String barCode) {
        return productRepository.existsByBarCode(barCode);
    }

    @Override
    public Optional<ProductModel> findByBarCode(String barCode) {
        return productRepository.findByBarCode(barCode);
    }

    @Override
    public List<ProductModel> findByExpirationDateLessThanEqual(LocalDate fromDate) {
        return productRepository.findByExpirationDateLessThanEqual(fromDate);
    }

}
