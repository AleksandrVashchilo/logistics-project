package com.java_43e.logisticsproject.service.database;

import com.java_43e.logisticsproject.entity.Employee;

import java.util.List;

public interface EmployeeDatabaseService {
    List<Employee> getEmployee();
    Employee findById(Integer id);

    void saveOrUpdateCustomer(Employee employee);
}
