package com.java_43e.logisticsproject.service.database;

import com.java_43e.logisticsproject.entity.Employee;

import java.util.List;

public interface EmployeeDatabaseService {
    /**
     * @return receiving all employees
     */
    List<Employee> getAllEmployees();

    /**
     * @param id
     * @return getting an employee with the requested id
     */
    Employee findById(Integer id);

    /**
     * employee save, update
     * @param employee
     */
    void saveOrUpdateEmployee(Employee employee);
}
