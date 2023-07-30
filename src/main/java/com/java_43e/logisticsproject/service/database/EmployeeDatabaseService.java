package com.java_43e.logisticsproject.service.database;

import com.java_43e.logisticsproject.entity.Employee;

import java.util.List;

public interface EmployeeDatabaseService {
    List<Employee> getAllEmployees();
    Employee findById(Integer id);

    void saveOrUpdateEmployee(Employee employee);
}
