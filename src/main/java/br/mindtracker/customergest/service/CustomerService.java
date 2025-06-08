package br.mindtracker.customergest.service;

import br.mindtracker.customergest.dto.request.CustomerRequest;
import br.mindtracker.customergest.dto.response.CustomerResponse;
import br.mindtracker.customergest.entity.CustomerEntity;
import br.mindtracker.customergest.mapper.CustomerMapper;
import br.mindtracker.customergest.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Transactional
    public CustomerResponse createCustomer(CustomerRequest request) {
        log.info("Creating new customer with email: {}", request.getEmail());
        
        if (customerRepository.existsByEmail(request.getEmail())) {
            log.warn("Customer with email {} already exists", request.getEmail());
            throw new IllegalArgumentException("Customer with this email already exists");
        }
        
        CustomerEntity customerEntity = customerMapper.toEntity(request);
        CustomerEntity savedCustomer = customerRepository.save(customerEntity);
        log.info("Customer created successfully with ID: {}", savedCustomer.getId());
        
        return customerMapper.toResponse(savedCustomer);
    }

    public CustomerResponse findByCpf(String cpf) {
        log.info("Searching for customer with CPF: {}", cpf);
        CustomerEntity customerEntity = customerRepository.findByCpf(cpf)
                .orElseThrow(() -> {
                    log.warn("Customer with CPF {} not found", cpf);
                    return new IllegalArgumentException("Customer not found with CPF: " + cpf);
                });
        
        return customerMapper.toResponse(customerEntity);
    }

    @Transactional
    public CustomerResponse deactivateCustomer(String cpf) {
        log.info("Deactivating customer with CPF: {}", cpf);
        CustomerEntity customerEntity = customerRepository.findByCpf(cpf)
                .orElseThrow(() -> {
                    log.warn("Customer with CPF {} not found", cpf);
                    return new IllegalArgumentException("Customer not found with CPF: " + cpf);
                });

        if (!customerEntity.isActive()) {
            log.warn("Customer with CPF {} is already deactivated", cpf);
            throw new IllegalStateException("Customer is already deactivated");
        }

        customerEntity.setActive(false);
        CustomerEntity updatedCustomer = customerRepository.save(customerEntity);
        log.info("Customer with CPF {} deactivated successfully", cpf);
        
        return customerMapper.toResponse(updatedCustomer);
    }
} 