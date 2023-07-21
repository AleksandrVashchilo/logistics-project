package com.java_43e.logisticsproject.controller;

import com.java_43e.logisticsproject.entity.Order;
import com.java_43e.logisticsproject.service.database.OrderDatabaseService;
import com.java_43e.logisticsproject.service.impl.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderDatabaseService orderDatabaseService;

    @GetMapping
    public List<Order> getAllOrders() {

        return orderDatabaseService.getOrder();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Integer id) {
        try {
            Order order = orderDatabaseService.findById(id);
            return ResponseEntity.ok(order);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Order> addOrder(@RequestBody Order order) {
        orderDatabaseService.saveOrUpdateOrder(order);
        return ResponseEntity.ok(order);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Integer id, @RequestBody Order order) {
        try {
            orderDatabaseService.findById(id);
            orderDatabaseService.saveOrUpdateOrder(order);
            return ResponseEntity.ok(order);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
