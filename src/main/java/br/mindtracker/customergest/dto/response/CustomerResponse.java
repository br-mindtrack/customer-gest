package br.mindtracker.customergest.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Response object containing customer information")
public class CustomerResponse {
    
    @Schema(description = "Unique identifier of the customer", example = "1")
    private Long id;
    
    @Schema(description = "Customer's full name", example = "John Doe")
    private String name;
    
    @Schema(description = "Customer's email address", example = "john.doe@example.com")
    private String email;
    
    @Schema(description = "Customer's CPF (Brazilian tax ID)", example = "123.456.789-00")
    private String cpf;
    
    @Schema(description = "Customer's phone number", example = "+55 11 98765-4321")
    private String phone;
    
    @Schema(description = "Customer's address", example = "123 Main St, City, State, 12345")
    private String address;
    
    @Schema(description = "Customer's active status", example = "true")
    private boolean active;
} 