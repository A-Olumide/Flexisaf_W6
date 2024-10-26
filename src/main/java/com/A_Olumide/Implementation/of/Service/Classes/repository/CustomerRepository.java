package com.A_Olumide.Implementation.of.Service.Classes.repository;

import com.A_Olumide.Implementation.of.Service.Classes.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmailIgnoreCase (String email);
}
