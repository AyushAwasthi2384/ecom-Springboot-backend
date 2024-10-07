package com.clothing.shoppingwebapp.controller;

import com.clothing.shoppingwebapp.model.Customer;
import com.clothing.shoppingwebapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // Get all customers (Admin view)
    @GetMapping("/admin")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    // Get a customer by ID
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    // Create a new customer (Signup)
    @PostMapping("/signup")
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    // Login customer
    @PostMapping("/login")
    public String loginCustomer(@RequestBody Customer customer) {
        boolean isAuthenticated = customerService.authenticateCustomer(customer.getEmail(), customer.getPassword());
        return isAuthenticated ? "Login successful" : "Invalid credentials";
    }

    // Update customer details
    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customerDetails) {
        return customerService.updateCustomer(id, customerDetails);
    }

    // Delete customer (Admin access)
    @DeleteMapping("/admin/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }
}
