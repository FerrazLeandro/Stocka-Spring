package br.com.stocka.stockaspring.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductDto {

    private Long stockId;

    @NotBlank(message = "Name must contain a valid value")
    @Size(min = 3, max = 40, message = "Name must be between 3 and 40 characters")
    private String name;

    @NotNull(message = "Type in stock must contain a valid value")
    private String type;

    @NotNull(message = "Price must contain a valid value")
    @Min(value = 0, message = "Price must be greater than or equal to 0")
    private BigDecimal price;

    @NotNull(message = "Cost must contain a valid value")
    @Min(value = 0, message = "Cost must be greater than or equal to 0")
    private BigDecimal cost;

    @NotNull(message = "Quantity in stock must contain a valid value")
    @Min(value = 0, message = "Quantity in stock must be greater than or equal to 0")
    private Integer quantityInStock;

    @Min(value = 0, message = "Competition Price must be greater than or equal to 0")
    private BigDecimal competitionPrice;

    @NotBlank(message = "Bar code must contain a valid value")
    private String barCode;

    @NotNull(message = "Expiration date must contain a valid value")
    private LocalDate expirationDate;

    @NotNull(message = "Physical postion must contain a valid value")
    private String physicalPosition;
}
