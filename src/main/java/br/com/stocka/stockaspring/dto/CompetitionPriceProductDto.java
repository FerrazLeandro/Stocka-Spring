package br.com.stocka.stockaspring.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class CompetitionPriceProductDto {
    @Min(value = 0, message = "Competition Price must be greater than or equal to 0")
    private BigDecimal competitionPrice;
}
