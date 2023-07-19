package com.java_43e.logisticsproject.controller;

import com.java_43e.logisticsproject.entity.Employee;
import com.java_43e.logisticsproject.service.database.EmployeeDatabaseService;
import com.java_43e.logisticsproject.service.impl.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeDatabaseService employeeDatabaseService;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeDatabaseService.getEmployee();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) {
        try {
            Employee employee = employeeDatabaseService.findById(id);
            return ResponseEntity.ok(employee);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        employeeDatabaseService.saveOrUpdateCustomer(employee);
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Integer id, @RequestBody Employee employee) {
        try {
            employeeDatabaseService.findById(id);
            employeeDatabaseService.saveOrUpdateCustomer(employee);
            return ResponseEntity.ok(employee);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
