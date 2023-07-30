package com.java_43e.logisticsproject.service.database;

import com.java_43e.logisticsproject.entity.Customer;

import java.util.List;

public interface CustomerDatabaseService {

    List<Customer> getCustomer();
    Customer findById(Integer id);
    Customer saveOrUpdateCustomer(Customer customer);
}
