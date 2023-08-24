package br.com.stocka.stockaspring.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class GiftListDto {

    private List<ProductDto> products;
   
    @NotBlank(message = "Description must contain a valid value")
    @Size(min = 3, max = 40, message = "Description must be between 3 and 40 characters")
    private String description;
    
}
