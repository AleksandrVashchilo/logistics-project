package com.java_43e.logisticsproject.service.impl;

import com.java_43e.logisticsproject.entity.Employee;
import com.java_43e.logisticsproject.repository.EmployeeRepository;
import com.java_43e.logisticsproject.service.database.EmployeeDatabaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
public class EmployeeDatabaseServiceImpl implements EmployeeDatabaseService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(Integer id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee with id: " + id + " not found"));
    }

    @Override
    @Transactional
    public void saveOrUpdateEmployee(Employee employee) {
        String encodedPassword = passwordEncoder.encode(employee.getPassword());
        employee.setPassword(encodedPassword);
        employeeRepository.save(employee);
    }
}
