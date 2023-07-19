package com.java_43e.logisticsproject.service.database;

import com.java_43e.logisticsproject.entity.Order;

import java.util.List;

public interface OrderDatabaseService {

    List<Order> getOrder();
    Order findById(Integer id);

    void saveOrUpdateOrder(Order order);
}
