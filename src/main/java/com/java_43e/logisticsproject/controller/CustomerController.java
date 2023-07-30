package com.java_43e.logisticsproject.controller;

import com.java_43e.logisticsproject.entity.Customer;
import com.java_43e.logisticsproject.service.database.CustomerDatabaseService;
import com.java_43e.logisticsproject.service.impl.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerDatabaseService customerDatabaseService;

    @GetMapping("get-all")
    public List<Customer> getAllCustomers() {
        return customerDatabaseService.getCustomer();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Integer id) {
        try {
            Customer customer = customerDatabaseService.findById(id);
            return ResponseEntity.ok(customer);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        customerDatabaseService.saveOrUpdateCustomer(customer);
        return ResponseEntity.ok(customer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Integer id, @RequestBody Customer customer) {
        try {
            customerDatabaseService.findById(id);
            customerDatabaseService.saveOrUpdateCustomer(customer);
            return ResponseEntity.ok(customer);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}