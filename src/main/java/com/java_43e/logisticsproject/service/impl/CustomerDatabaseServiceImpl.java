package com.java_43e.logisticsproject.service.impl;

import com.java_43e.logisticsproject.entity.Customer;
import com.java_43e.logisticsproject.repository.CustomerRepository;
import com.java_43e.logisticsproject.service.database.CustomerDatabaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerDatabaseServiceImpl implements CustomerDatabaseService {

    private final CustomerRepository customerRepository;

    @Override
    public List<Customer> getCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(Integer id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer with id: " + id + " not found"));
    }

    @Override
    public void saveOrUpdateCustomer(Customer customer) {

        customerRepository.save(customer);
    }
}