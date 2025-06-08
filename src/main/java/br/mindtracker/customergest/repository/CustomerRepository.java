package br.mindtracker.customergest.repository;

import br.mindtracker.customergest.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    boolean existsByEmail(String email);
    Optional<CustomerEntity> findByCpf(String cpf);
} 