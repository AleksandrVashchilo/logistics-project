package com.java_43e.logisticsproject.service.database;

import com.java_43e.logisticsproject.entity.Order;

import java.util.List;

public interface OrderDatabaseService {
    /**
     * get  all orders
     *
     * @return
     */
    List<Order> getOrder();

    /**
     * @param id
     * @return getting an order by id
     */
    Order findById(Integer id);

    /**
     * order save, update
     *
     * @param order
     */

    void saveOrUpdateOrder(Order order);
}
