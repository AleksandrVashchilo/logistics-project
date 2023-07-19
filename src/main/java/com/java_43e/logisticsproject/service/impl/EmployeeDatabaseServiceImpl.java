package com.java_43e.logisticsproject.service.impl;

import com.java_43e.logisticsproject.entity.Employee;
import com.java_43e.logisticsproject.repository.EmployeeRepository;
import com.java_43e.logisticsproject.service.database.EmployeeDatabaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class EmployeeDatabaseServiceImpl implements EmployeeDatabaseService {

    private final EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(Integer id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee with id: " + id + " not found"));
    }

    @Override
    public void saveOrUpdateCustomer(Employee employee) {
        employeeRepository.save(employee);
    }
}
