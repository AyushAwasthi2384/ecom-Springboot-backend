package com.clothing.shoppingwebapp.repository;

import com.clothing.shoppingwebapp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email); // Find customer by email for login
}
