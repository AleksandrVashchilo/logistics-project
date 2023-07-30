package com.java_43e.logisticsproject.controller;
import static org.mockito.Mockito.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java_43e.logisticsproject.entity.Employee;
import com.java_43e.logisticsproject.service.database.EmployeeDatabaseService;
import com.java_43e.logisticsproject.service.impl.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeDatabaseService employeeDatabaseService;

    @Test
    public void testGetAllEmployees() throws Exception {
        List<Employee> mockEmployeeList = new ArrayList<>();
        Employee employee1 = new Employee();
        employee1.setEmployeeName("John Doe");
        Employee employee2 = new Employee();
        employee2.setEmployeeName("Jane Smith");
        mockEmployeeList.add(employee1);
        mockEmployeeList.add(employee2);

        // Устанавливаем мок-данные для сервиса
        when(employeeDatabaseService.getAllEmployees()).thenReturn(mockEmployeeList);

        // Выполняем GET-запрос на эндпоинт /api/employees/getAll
        mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/getAll"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].employeeName").value("John Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].employeeName").value("Jane Smith"));
    }

    @Test
    public void testGetEmployeeById_ValidId_Success() throws Exception {
        int employeeId = 1;
        Employee mockEmployee = new Employee();
        mockEmployee.setEmployeeName("John Doe");
        // Здесь вы можете установить значения для других полей класса Employee для объекта mockEmployee
        // Пример:
        // mockEmployee.setPosition("Manager");
        // ...

        when(employeeDatabaseService.findById(employeeId)).thenReturn(mockEmployee);

        // Выполняем GET-запрос на эндпоинт /api/employees/{id}
        mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/{id}", employeeId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.employeeName").value("John Doe"));
    }

    @Test
    public void testGetEmployeeById_InvalidId_NotFound() throws Exception {
        int nonExistentEmployeeId = 100; // Несуществующий ID
        when(employeeDatabaseService.findById(anyInt())).thenThrow(new ResourceNotFoundException());

        // Выполняем GET-запрос на эндпоинт /api/employees/{id} с несуществующим ID
        mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/{id}", nonExistentEmployeeId))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testAddEmployee() throws Exception {
        Employee newEmployee = new Employee();
        newEmployee.setEmployeeName("New Employee");
        // Здесь вы можете установить значения для других полей класса Employee для объекта newEmployee
        // Пример:
        // newEmployee.setPosition("Assistant");
        // ...

        // Выполняем POST-запрос на эндпоинт /api/employees/add
        mockMvc.perform(MockMvcRequestBuilders.post("/api/employees/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(newEmployee)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.employeeName").value(newEmployee.getEmployeeName()));
    }

    @Test
    public void testUpdateEmployee_ValidId() throws Exception {
        int employeeId = 1;
        Employee updatedEmployee = new Employee();
        updatedEmployee.setEmployeeName("Updated Employee");
        // Здесь вы можете установить значения для других полей класса Employee, если они требуют обновления.
        // Пример:
        // updatedEmployee.setPosition("Updated Position");
        // ...

        when(employeeDatabaseService.findById(employeeId)).thenReturn(updatedEmployee);

        // Выполняем PUT-запрос на эндпоинт /api/employees/{id}
        mockMvc.perform(MockMvcRequestBuilders.put("/api/employees/{id}", employeeId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updatedEmployee)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.employeeName").value(updatedEmployee.getEmployeeName()));
    }

    @Test
    public void testUpdateEmployee_InvalidId_NotFound() throws Exception {
        int employeeId = 100; // Не существующий ID
        Employee updatedEmployee = new Employee();
        updatedEmployee.setEmployeeName("Updated Employee");
        // Здесь вы можете установить значения для других полей класса Employee, если они требуют обновления.
        // Пример:
        // updatedEmployee.setPosition("Updated Position");
        // ...

        when(employeeDatabaseService.findById(employeeId)).thenThrow(new ResourceNotFoundException());

        // Выполняем PUT-запрос на эндпоинт /api/employees/{id}
        mockMvc.perform(MockMvcRequestBuilders.put("/api/employees/{id}", employeeId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updatedEmployee)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}
