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
import br.com.stocka.stockaspring.dto.GiftListDto;
import br.com.stocka.stockaspring.model.ProductModel;
import br.com.stocka.stockaspring.model.GiftListModel;
import br.com.stocka.stockaspring.service.giftList.GiftListServiceImpl;
import br.com.stocka.stockaspring.service.product.ProductServiceImpl;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
//@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@RequestMapping("/giftList")
public class GiftListController {

    final GiftListServiceImpl giftListServiceImpl;
    final ProductServiceImpl productServiceImpl;

    public GiftListController(GiftListServiceImpl giftListServiceImpl, ProductServiceImpl productServiceImpl) {
        this.giftListServiceImpl = giftListServiceImpl;
        this.productServiceImpl = productServiceImpl;
    }

    @PostMapping
    public ResponseEntity<Object> saveGiftList(@RequestBody @Valid GiftListDto giftListDto) {
        if (giftListServiceImpl.existsByDescription(giftListDto.getDescription())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Conflict: Description " + giftListDto.getDescription() + " is already in use!");
        }

        var giftListModel = new GiftListModel();
        BeanUtils.copyProperties(giftListDto, giftListModel);
        giftListModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(giftListServiceImpl.save(giftListModel));
    }

    @PostMapping("/{giftList_id}/addProduct")
    public ResponseEntity<Object> saveProduct(@PathVariable(value = "giftList_id") Long giftList_id,
            @RequestBody ProductDto productDto) {
        Optional<GiftListModel> giftListModelOptional = giftListServiceImpl.findById(giftList_id);
        if (!giftListModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Gift List not found.");
        }

        GiftListModel giftList = giftListModelOptional.get();

        Optional<ProductModel> productModelOptional = productServiceImpl.findByName(productDto.getName());
        if (productModelOptional.isPresent()) {
            ProductModel productModel = productModelOptional.get();
            productModel.setGiftList(giftList);
            productServiceImpl.save(productModel);
        } else {
            ProductModel productModel = new ProductModel();
            BeanUtils.copyProperties(productDto, productModel);
            productModel.setGiftList(giftList);
            productServiceImpl.save(productModel);
        }

        return ResponseEntity.ok("Product added to the Gift List successfully.");
    }

    @GetMapping
    public ResponseEntity<List<GiftListModel>> getAllGiftLists() {
        return ResponseEntity.status(HttpStatus.OK).body(giftListServiceImpl.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneGiftList(@PathVariable(value = "id") Long id) {
        Optional<GiftListModel> giftListOptional = giftListServiceImpl.findById(id);
        if (!giftListOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Gift List not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(giftListOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteGiftList(@PathVariable(value = "id") Long id) {
        Optional<GiftListModel> giftListOptional = giftListServiceImpl.findById(id);
        if (!giftListOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Gift List not found.");
        }
        giftListServiceImpl.delete(giftListOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Gift List deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateGiftList(@PathVariable(value = "id") Long id,
            @RequestBody @Valid GiftListModel giftListDto) {
        Optional<GiftListModel> giftListOptional = giftListServiceImpl.findById(id);
        if (!giftListOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Gift List not found.");
        }
        var giftList = new GiftListModel();
        BeanUtils.copyProperties(giftListDto, giftList);
        giftList.setGiftListId(giftListOptional.get().getGiftListId());
        return ResponseEntity.status(HttpStatus.OK).body(giftListServiceImpl.save(giftList));
    }

}
