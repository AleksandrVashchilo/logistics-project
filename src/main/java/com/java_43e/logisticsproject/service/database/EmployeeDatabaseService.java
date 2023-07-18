package com.java_43e.logisticsproject.service.database;

import com.java_43e.logisticsproject.entity.Customer;
import com.java_43e.logisticsproject.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeDatabaseService {
    List<Employee> getEmployee();
    Optional<Employee> findById(Integer id);
    void addEmployee(Employee employee);

    boolean updateEmployee(Integer id, Employee updatedEmployee);
}
