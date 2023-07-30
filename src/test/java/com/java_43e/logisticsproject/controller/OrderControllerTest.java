package com.java_43e.logisticsproject.controller;
import static org.mockito.Mockito.*;

import com.java_43e.logisticsproject.entity.Order;
import com.java_43e.logisticsproject.service.database.OrderDatabaseService;
import com.java_43e.logisticsproject.service.impl.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderDatabaseService orderDatabaseService;

    @Test
    public void testGetAllOrders() throws Exception {
        // Устанавливаем мок-данные для сервиса
        List<Order> mockOrderList = new ArrayList<>();
        Order order1 = new Order();
        order1.setOrderId(1);
        order1.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        order1.setCustomerId(101);
        order1.setEmployeeID(201);
        order1.setVehicleId(301);
        order1.setClosed(false);
        order1.setDeleted(false);
        mockOrderList.add(order1);

        when(orderDatabaseService.getOrder()).thenReturn(mockOrderList);

        // Выполняем GET-запрос на эндпоинт /api/order
        mockMvc.perform(MockMvcRequestBuilders.get("/api/order"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].orderId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].customerId").value(101))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].employeeID").value(201))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].vehicleId").value(301))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].isClosed").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].isDeleted").value(false));
    }

    @Test
    public void testGetOrderById_ValidId_Success() throws Exception {
        int orderId = 1;
        Order mockOrder = new Order();
        mockOrder.setOrderId(orderId);
        mockOrder.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        mockOrder.setCustomerId(101);
        mockOrder.setEmployeeID(201);
        mockOrder.setVehicleId(301);
        mockOrder.setClosed(false);
        mockOrder.setDeleted(false);

        when(orderDatabaseService.findById(orderId)).thenReturn(mockOrder);

        // Выполняем GET-запрос на эндпоинт /api/order/{id}
        mockMvc.perform(MockMvcRequestBuilders.get("/api/order/{id}", orderId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.orderId").value(orderId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.customerId").value(101))
                .andExpect(MockMvcResultMatchers.jsonPath("$.employeeID").value(201))
                .andExpect(MockMvcResultMatchers.jsonPath("$.vehicleId").value(301))
                .andExpect(MockMvcResultMatchers.jsonPath("$.isClosed").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$.isDeleted").value(false));
    }

    @Test
    public void testGetOrderById_InvalidId_NotFound() throws Exception {
        int nonExistentOrderId = 100; // Несуществующий ID
        when(orderDatabaseService.findById(anyInt())).thenThrow(new ResourceNotFoundException());

        // Выполняем GET-запрос на эндпоинт /api/order/{id} с несуществующим ID
        mockMvc.perform(MockMvcRequestBuilders.get("/api/order/{id}", nonExistentOrderId))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testAddOrder() throws Exception {
        Order newOrder = new Order();
        newOrder.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        newOrder.setCustomerId(101);
        newOrder.setEmployeeID(201);
        newOrder.setVehicleId(301);
        newOrder.setClosed(false);
        newOrder.setDeleted(false);

        // Выполняем POST-запрос на эндпоинт /api/order
        mockMvc.perform(MockMvcRequestBuilders.post("/api/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"createdAt\":\"2023-07-30T12:34:56.789Z\", \"customerId\":101, \"employeeID\":201, \"vehicleId\":301, \"isClosed\":false, \"isDeleted\":false }"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customerId").value(101))
                .andExpect(MockMvcResultMatchers.jsonPath("$.employeeID").value(201))
                .andExpect(MockMvcResultMatchers.jsonPath("$.vehicleId").value(301))
                .andExpect(MockMvcResultMatchers.jsonPath("$.isClosed").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$.isDeleted").value(false));
    }

    @Test
    public void testUpdateOrder_ValidId() throws Exception {
        int orderId = 1;
        Order updatedOrder = new Order();
        updatedOrder.setOrderId(orderId);
        updatedOrder.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        updatedOrder.setCustomerId(101);
        updatedOrder.setEmployeeID(201);
        updatedOrder.setVehicleId(301);
        updatedOrder.setClosed(true);
        updatedOrder.setDeleted(false);

        when(orderDatabaseService.findById(orderId)).thenReturn(updatedOrder);

        // Выполняем PUT-запрос на эндпоинт /api/order/{id}
        mockMvc.perform(MockMvcRequestBuilders.put("/api/order/{id}", orderId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"createdAt\":\"2023-07-30T12:34:56.789Z\", \"customerId\":101, \"employeeID\":201, \"vehicleId\":301, \"isClosed\":true, \"isDeleted\":false }"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.orderId").value(orderId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.customerId").value(101))
                .andExpect(MockMvcResultMatchers.jsonPath("$.employeeID").value(201))
                .andExpect(MockMvcResultMatchers.jsonPath("$.vehicleId").value(301))
                .andExpect(MockMvcResultMatchers.jsonPath("$.isClosed").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.isDeleted").value(false));
    }

    @Test
    public void testUpdateOrder_InvalidId_NotFound() throws Exception {
        int orderId = 100; // Не существующий ID
        Order updatedOrder = new Order();
        updatedOrder.setOrderId(orderId);
        updatedOrder.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        updatedOrder.setCustomerId(101);
        updatedOrder.setEmployeeID(201);
        updatedOrder.setVehicleId(301);
        updatedOrder.setClosed(true);
        updatedOrder.setDeleted(false);

        when(orderDatabaseService.findById(orderId)).thenThrow(new ResourceNotFoundException());

        // Выполняем PUT-запрос на эндпоинт /api/order/{id}
        mockMvc.perform(MockMvcRequestBuilders.put("/api/order/{id}", orderId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"createdAt\":\"2023-07-30T12:34:56.789Z\", \"customerId\":101, \"employeeID\":201, \"vehicleId\":301, \"isClosed\":true, \"isDeleted\":false }"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
