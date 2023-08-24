package br.com.stocka.stockaspring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {
    
    @NotBlank(message = "Username must contain a valid value")
    @Size(min = 3, max = 40, message = "Username must be between 3 and 40 characters")
    private String username;
    
    @NotBlank(message = "Password must contain a valid value")
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    private String password;
    
}
