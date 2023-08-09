package com.java_43e.logisticsproject.controller;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java_43e.logisticsproject.entity.Customer;
import com.java_43e.logisticsproject.service.database.CustomerDatabaseService;
import com.java_43e.logisticsproject.service.impl.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerDatabaseService customerDatabaseService;

    @Test
    public void testGetAllCustomers() throws Exception {
        List<Customer> mockCustomerList = new ArrayList<>();
        Customer customer1 = new Customer();
        customer1.setCustomerName("Customer 1");
        Customer customer2 = new Customer();
        customer2.setCustomerName("Customer 2");
        mockCustomerList.add(customer1);
        mockCustomerList.add(customer2);

        // Устанавливаем мок-данные для сервиса
        when(customerDatabaseService.getCustomer()).thenReturn(mockCustomerList);

        // Выполняем GET-запрос на эндпоинт /api/customers/get-all
        mockMvc.perform(MockMvcRequestBuilders.get("/api/customers/get-all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].customerName").value("Customer 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].customerName").value("Customer 2"));
    }

    @Test
    public void testGetCustomerById_ValidId_Success() throws Exception {
        int customerId = 1;
        Customer mockCustomer = new Customer();
        mockCustomer.setCustomerName("Customer 1");
        // Здесь вы можете установить значения для других полей класса Customer для объекта mockCustomer
        // Пример:
        // mockCustomer.setContactName("John Doe");
        // mockCustomer.setAddress("123 Main Street");
        // mockCustomer.setCountry("USA");
        // ...

        when(customerDatabaseService.findById(customerId)).thenReturn(mockCustomer);

        // Выполняем GET-запрос на эндпоинт /api/customers/{id}
        mockMvc.perform(MockMvcRequestBuilders.get("/api/customers/{id}", customerId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customerName").value("Customer 1"));
    }

    @Test
    public void testGetCustomerById_InvalidId_NotFound() throws Exception {
        int nonExistentCustomerId = 100; // Несуществующий ID
        when(customerDatabaseService.findById(anyInt())).thenThrow(new ResourceNotFoundException());

        // Выполняем GET-запрос на эндпоинт /api/customers/{id} с несуществующим ID
        mockMvc.perform(MockMvcRequestBuilders.get("/api/customers/{id}", nonExistentCustomerId))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testAddCustomer() throws Exception {
        Customer newCustomer = new Customer();
        newCustomer.setCustomerName("New Customer");
        // Здесь вы можете установить значения для других полей класса Customer для объекта newCustomer
        // Пример:
        // newCustomer.setContactName("Jane Doe");
        // newCustomer.setAddress("456 Elm Street");
        // newCustomer.setCountry("Canada");
        // ...

        // Выполняем POST-запрос на эндпоинт /api/customers
        mockMvc.perform(MockMvcRequestBuilders.post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(newCustomer)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customerName").value(newCustomer.getCustomerName()));
    }

    @Test
    public void testUpdateCustomer_ValidId() throws Exception {
        int customerId = 1;
        Customer updatedCustomer = new Customer();
        updatedCustomer.setCustomerName("Updated Customer 1");
        // Здесь вы можете установить значения для других полей класса Customer, если они требуют обновления.
        // Пример:
        // updatedCustomer.setContactName("Updated Contact");
        // updatedCustomer.setAddress("Updated Address");
        // updatedCustomer.setCountry("Updated Country");
        // ...

        when(customerDatabaseService.findById(customerId)).thenReturn(updatedCustomer);

        // Выполняем PUT-запрос на эндпоинт /api/customers/{id}
        mockMvc.perform(MockMvcRequestBuilders.put("/api/customers/{id}", customerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updatedCustomer)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customerName").value(updatedCustomer.getCustomerName()));
    }

    @Test
    public void testUpdateCustomer_InvalidId_NotFound() throws Exception {
        int customerId = 100; // Не существующий ID
        Customer updatedCustomer = new Customer();
        updatedCustomer.setCustomerName("Updated Customer");
        // Здесь вы можете установить значения для других полей класса Customer, если они требуют обновления.
        // Пример:
        // updatedCustomer.setContactName("Updated Contact");
        // updatedCustomer.setAddress("Updated Address");
        // updatedCustomer.setCountry("Updated Country");
        // ...

        when(customerDatabaseService.findById(customerId)).thenThrow(new ResourceNotFoundException());

        // Выполняем PUT-запрос на эндпоинт /api/customers/{id}
        mockMvc.perform(MockMvcRequestBuilders.put("/api/customers/{id}", customerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updatedCustomer)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
