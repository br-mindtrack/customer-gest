package br.mindtracker.customergest.mapper;

import br.mindtracker.customergest.dto.request.CustomerRequest;
import br.mindtracker.customergest.dto.response.CustomerResponse;
import br.mindtracker.customergest.entity.CustomerEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomerMapper {

    public CustomerEntity toEntity(CustomerRequest request) {
        log.debug("Converting CustomerRequest to CustomerEntity");
        return CustomerEntity.builder()
                .name(request.getName())
                .email(request.getEmail())
                .cpf(request.getCpf())
                .phone(request.getPhone())
                .address(request.getAddress())
                .build();
    }

    public CustomerResponse toResponse(CustomerEntity entity) {
        log.debug("Converting CustomerEntity to CustomerResponse");
        return CustomerResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .cpf(entity.getCpf())
                .phone(entity.getPhone())
                .address(entity.getAddress())
                .build();
    }
} 