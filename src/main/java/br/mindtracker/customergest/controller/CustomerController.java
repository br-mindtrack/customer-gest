package br.mindtracker.customergest.controller;

import br.mindtracker.customergest.dto.request.CustomerRequest;
import br.mindtracker.customergest.dto.response.CustomerResponse;
import br.mindtracker.customergest.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
@Tag(name = "Customer Management", description = "APIs for managing customer information")
public class CustomerController {

    private final CustomerService customerService;

    @Operation(
        summary = "Create a new customer",
        description = "Creates a new customer with the provided information"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Customer created successfully",
            content = @Content(schema = @Schema(implementation = CustomerResponse.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid input data"
        ),
        @ApiResponse(
            responseCode = "409",
            description = "Customer with this email already exists"
        )
    })
    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(
            @Valid @RequestBody CustomerRequest request) {
        log.info("Received request to create new customer");
        CustomerResponse response = customerService.createCustomer(request);
        log.info("Customer created successfully with ID: {}", response.getId());
        return ResponseEntity.ok(response);
    }

    @Operation(
        summary = "Find customer by CPF",
        description = "Retrieves customer information using their CPF"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Customer found successfully",
            content = @Content(schema = @Schema(implementation = CustomerResponse.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Customer not found"
        )
    })
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<CustomerResponse> getCustomerByCpf(
            @Parameter(description = "CPF of the customer to find", example = "123.456.789-00")
            @PathVariable String cpf) {
        log.info("Received request to find customer by CPF: {}", cpf);
        CustomerResponse response = customerService.findByCpf(cpf);
        log.info("Customer found successfully with ID: {}", response.getId());
        return ResponseEntity.ok(response);
    }

    @Operation(
        summary = "Deactivate customer",
        description = "Deactivates a customer using their CPF"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Customer deactivated successfully",
            content = @Content(schema = @Schema(implementation = CustomerResponse.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Customer not found"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Customer is already deactivated"
        )
    })
    @PatchMapping("/cpf/{cpf}/deactivate")
    public ResponseEntity<CustomerResponse> deactivateCustomer(
            @Parameter(description = "CPF of the customer to deactivate", example = "123.456.789-00")
            @PathVariable String cpf) {
        log.info("Received request to deactivate customer with CPF: {}", cpf);
        CustomerResponse response = customerService.deactivateCustomer(cpf);
        log.info("Customer deactivated successfully with ID: {}", response.getId());
        return ResponseEntity.ok(response);
    }
} 