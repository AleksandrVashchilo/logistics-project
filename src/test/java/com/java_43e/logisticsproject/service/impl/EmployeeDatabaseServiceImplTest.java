package com.java_43e.logisticsproject.service.impl;
import com.java_43e.logisticsproject.entity.Employee;
import com.java_43e.logisticsproject.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
public class EmployeeDatabaseServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private EmployeeDatabaseServiceImpl employeeDatabaseService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllEmployees() {
        List<Employee> mockEmployeeList = new ArrayList<>();
        mockEmployeeList.add(new Employee());
        mockEmployeeList.add(new Employee());

        when(employeeRepository.findAll()).thenReturn(mockEmployeeList);

        List<Employee> result = employeeDatabaseService.getAllEmployees();

        assertEquals(2, result.size());
    }

    @Test
    public void testFindById_ValidId() {
        Employee mockEmployee = new Employee();
        mockEmployee.setEmployeeID(1);

        when(employeeRepository.findById(1)).thenReturn(Optional.of(mockEmployee));

        Employee result = employeeDatabaseService.findById(1);

        assertEquals(mockEmployee, result);
    }

    @Test
    public void testFindById_InvalidId() {
        when(employeeRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> employeeDatabaseService.findById(100));
    }

    @Test
    public void testSaveOrUpdateEmployee() {
        Employee employeeToSave = new Employee();
        // Здесь можно задать необходимые значения для объекта employeeToSave

        when(passwordEncoder.encode(any(String.class))).thenReturn("encodedPassword");
        when(employeeRepository.save(any(Employee.class))).thenReturn(employeeToSave);

        employeeDatabaseService.saveOrUpdateEmployee(employeeToSave);

        // Дополнительные проверки, если необходимо
        assertEquals("encodedPassword", employeeToSave.getPassword());
    }
}
