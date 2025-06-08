package br.mindtracker.customergest.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request object for creating or updating a customer")
public class CustomerRequest {
    
    @Schema(description = "Customer's full name", example = "John Doe")
    @NotBlank(message = "Name is required")
    private String name;
    
    @Schema(description = "Customer's email address", example = "john.doe@example.com")
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    
    @Schema(description = "Customer's CPF (Brazilian tax ID)", example = "123.456.789-00")
    @NotBlank(message = "CPF is required")
    @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$", message = "CPF must follow the pattern: XXX.XXX.XXX-XX")
    private String cpf;
    
    @Schema(description = "Customer's phone number", example = "+55 11 98765-4321")
    private String phone;
    
    @Schema(description = "Customer's address", example = "123 Main St, City, State, 12345")
    private String address;
} 