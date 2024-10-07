package com.clothing.shoppingwebapp.service;

import com.clothing.shoppingwebapp.model.Customer;
import com.clothing.shoppingwebapp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Create a new customer (Signup)
    public Customer createCustomer(Customer customer) {
        // Here you could add password hashing if needed
        return customerRepository.save(customer);
    }

    // Authenticate customer (Login)
    public boolean authenticateCustomer(String email, String password) {
        Optional<Customer> customer = customerRepository.findByEmail(email);
        // Validate password (in a real-world scenario, consider hashing the password)
        return customer.isPresent() && customer.get().getPassword().equals(password);
    }

    // Get all customers (Admin)
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Get a customer by ID
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with id: " + id));
    }

    // Update customer
    public Customer updateCustomer(Long id, Customer customerDetails) {
        Customer customer = getCustomerById(id);
        customer.setName(customerDetails.getName());
        customer.setEmail(customerDetails.getEmail());
        customer.setPassword(customerDetails.getPassword());
        return customerRepository.save(customer);
    }

    // Delete customer
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
