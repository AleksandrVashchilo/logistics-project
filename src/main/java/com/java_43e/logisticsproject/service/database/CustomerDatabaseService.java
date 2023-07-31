package com.java_43e.logisticsproject.service.database;

import com.java_43e.logisticsproject.entity.Customer;

import java.util.List;

public interface CustomerDatabaseService {
    /**
     * customer get
     *
     * @return
     */
    List<Customer> getCustomer();

    /**
     * customer find
     *
     * @param id
     * @return
     */
    Customer findById(Integer id);

    /**
     * customer save, update
     *
     * @param customer
     * @return
     */
    Customer saveOrUpdateCustomer(Customer customer);
}
