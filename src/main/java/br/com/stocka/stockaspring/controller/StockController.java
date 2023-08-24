package br.com.stocka.stockaspring.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.stocka.stockaspring.dto.ProductDto;
import br.com.stocka.stockaspring.dto.StockDto;
import br.com.stocka.stockaspring.model.ProductModel;
import br.com.stocka.stockaspring.model.StockModel;
import br.com.stocka.stockaspring.service.product.ProductServiceImpl;
import br.com.stocka.stockaspring.service.stock.StockServiceImpl;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
//@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@RequestMapping("/stocks")
public class StockController {

    final StockServiceImpl stockServiceImpl;
    final ProductServiceImpl productServiceImpl;

    public StockController(StockServiceImpl stockServiceImpl, ProductServiceImpl productServiceImpl) {
        this.stockServiceImpl = stockServiceImpl;
        this.productServiceImpl = productServiceImpl;
    }

    @PostMapping
    public ResponseEntity<Object> saveStock(@RequestBody @Valid StockDto stockDto) {
        if (stockServiceImpl.existsByDescription(stockDto.getDescription())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Conflict: Description " + stockDto.getDescription() + " is already in use!");
        }

        var stockModel = new StockModel();
        BeanUtils.copyProperties(stockDto, stockModel);
        stockModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(stockServiceImpl.save(stockModel));
    }

    @PostMapping("/{stockId}/addProduct")
    public ResponseEntity<Object> saveProduct(@PathVariable(value = "stockId") Long stockId,
            @RequestBody ProductDto productDto) {
        Optional<StockModel> stockModelOptional = stockServiceImpl.findById(stockId);
        if (!stockModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Stock not found.");
        }

        StockModel stockModel = stockModelOptional.get();

        Optional<ProductModel> productModelOptional = productServiceImpl.findByName(productDto.getName());
        if (productModelOptional.isPresent()) {
            ProductModel productModel = productModelOptional.get();
            productModel.setStock(stockModel);
            productServiceImpl.save(productModel);
        } else {
            ProductModel productModel = new ProductModel();
            BeanUtils.copyProperties(productDto, productModel);
            productModel.setStock(stockModel);
            productServiceImpl.save(productModel);
        }

        return ResponseEntity.ok("Product added to stock successfully.");
    }

    @GetMapping
    public ResponseEntity<List<StockModel>> getAllStocks() {
        return ResponseEntity.status(HttpStatus.OK).body(stockServiceImpl.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneStock(@PathVariable(value = "id") Long id) {
        Optional<StockModel> stockModelOptional = stockServiceImpl.findById(id);
        if (!stockModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Stock not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(stockModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteStock(@PathVariable(value = "id") Long id) {
        Optional<StockModel> stockModelOptional = stockServiceImpl.findById(id);
        if (!stockModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Stock not found.");
        }
        stockServiceImpl.delete(stockModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Stock deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateStock(@PathVariable(value = "id") Long id,
            @RequestBody @Valid StockModel stockDto) {
        Optional<StockModel> stockModelOptional = stockServiceImpl.findById(id);
        if (!stockModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Stock not found.");
        }
        var stockModel = new StockModel();
        BeanUtils.copyProperties(stockDto, stockModel);
        stockModel.setStockId(stockModelOptional.get().getStockId());
        return ResponseEntity.status(HttpStatus.OK).body(stockServiceImpl.save(stockModel));
    }

}
