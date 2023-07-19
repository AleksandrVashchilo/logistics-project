package com.java_43e.logisticsproject.service.impl;

import com.java_43e.logisticsproject.entity.Order;
import com.java_43e.logisticsproject.repository.OrderRepository;
import com.java_43e.logisticsproject.service.database.OrderDatabaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDatabaseServiceImpl implements OrderDatabaseService {

    private final OrderRepository orderRepository;
    @Override
    public List<Order> getOrder() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(Integer id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order with id: " + id + " not found"));
    }

    @Override
    public void saveOrUpdateOrder(Order order) {
        orderRepository.save(order);
    }
}
