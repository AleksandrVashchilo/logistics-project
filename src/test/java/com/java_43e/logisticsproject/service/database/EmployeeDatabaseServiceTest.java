package com.java_43e.logisticsproject.service.database;
import com.java_43e.logisticsproject.entity.Employee;
import com.java_43e.logisticsproject.service.impl.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EmployeeDatabaseServiceTest {

    @Mock
    private EmployeeDatabaseService employeeDatabaseService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllEmployees() {
        List<Employee> mockEmployeeList = new ArrayList<>();
        mockEmployeeList.add(new Employee());
        mockEmployeeList.add(new Employee());

        when(employeeDatabaseService.getAllEmployees()).thenReturn(mockEmployeeList);

        List<Employee> result = employeeDatabaseService.getAllEmployees();

        assertEquals(2, result.size());
    }

    @Test
    public void testFindById_ValidId() {
        Employee mockEmployee = new Employee();
        mockEmployee.setEmployeeID(1);

        when(employeeDatabaseService.findById(1)).thenReturn(mockEmployee);

        Employee result = employeeDatabaseService.findById(1);

        assertEquals(mockEmployee, result);
    }

    @Test
    public void testFindById_InvalidId() {
        when(employeeDatabaseService.findById(anyInt())).thenReturn(null);

        assertThrows(ResourceNotFoundException.class, () -> employeeDatabaseService.findById(100));
    }

    @Test
    public void testSaveOrUpdateEmployee() {
        Employee employeeToSave = new Employee();
        // Здесь можно задать необходимые значения для объекта employeeToSave

        // Указываем, что при вызове метода saveOrUpdateEmployee с любым объектом типа Employee
        // ничего не должно возвращаться, так как метод void
        doNothing().when(employeeDatabaseService).saveOrUpdateEmployee(any(Employee.class));

        // Вызываем тестируемый метод
        EmployeeDatabaseService employeeDatabaseServiceImpl = null;
        employeeDatabaseServiceImpl.saveOrUpdateEmployee(employeeToSave);

        // Проверяем, был ли метод вызван ровно один раз с корректным параметром
        verify(employeeDatabaseService, times(1)).saveOrUpdateEmployee(employeeToSave);
    }
}
