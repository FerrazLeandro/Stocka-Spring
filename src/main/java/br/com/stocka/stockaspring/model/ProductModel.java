package br.com.stocka.stockaspring.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_PRODUCT")
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "stock_id", nullable = true)
    private StockModel stock;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "giftList_id", nullable = true)
    private GiftListModel giftList;
    
    @Column(nullable = true, unique = true, name = "name")
    private String name;
    
    @Column(nullable = true, name = "type")
    private String type;
    
    @Column(nullable = true, name = "price")
    private BigDecimal price;
    
    @Column(nullable = true, name = "cost")
    private BigDecimal cost;
    
    @Column(nullable = true, name = "competition_price")
    private BigDecimal competitionPrice;
    
    @Column(nullable = true, name = "quantity_in_stock")
    private Integer quantityInStock;
    
    @Column(nullable = true, name = "bar_code")
    private String barCode;
    
    @Column(nullable = true, name = "expiration_date")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate expirationDate;

    @Column(nullable = true, name = "physical_position")
    private String physicalPosition;
    
    @Column(nullable = true, name = "registration_date")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @CreationTimestamp
    private LocalDateTime registrationDate;
}
