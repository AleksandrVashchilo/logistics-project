package com.java_43e.logisticsproject.repository;

import com.java_43e.logisticsproject.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testSaveEmployee() {
        // Создаем объект Employee
        Employee employee = new Employee();
        employee.setEmployeeName("John Doe");
        employee.setPassword("securepassword");
        employee.setPosition("Manager");
        employee.setBlocked(false);

        // Сохраняем Employee в базу данных
        Employee savedEmployee = employeeRepository.save(employee);

        // Проверяем, что объект успешно сохранен и имеет ненулевой идентификатор
        assertNotNull(savedEmployee.getEmployeeID());
    }

    @Test
    public void testFindEmployeeById() {
        // Создаем объект Employee
        Employee employee = new Employee();
        employee.setEmployeeName("Alice Smith");
        employee.setPassword("password123");
        employee.setPosition("Clerk");
        employee.setBlocked(true);

        // Сохраняем Employee в базу данных
        entityManager.persist(employee);
        entityManager.flush();

        // Ищем Employee по его идентификатору
        Optional<Employee> foundEmployee = employeeRepository.findById(employee.getEmployeeID());

        // Проверяем, что Employee найден и его значение соответствует ожидаемому
        assertTrue(foundEmployee.isPresent());
        assertEquals(employee.getEmployeeName(), foundEmployee.get().getEmployeeName());
    }

    @Test
    public void testFindAllEmployees() {
        // Создаем объекты Employee
        Employee employee1 = new Employee();
        employee1.setEmployeeName("Mary Johnson");
        employee1.setPassword("password456");
        employee1.setPosition("Driver");
        employee1.setBlocked(false);

        Employee employee2 = new Employee();
        employee2.setEmployeeName("Emma Lee");
        employee2.setPassword("pass123");
        employee2.setPosition("Supervisor");
        employee2.setBlocked(true);

        // Сохраняем Employee в базу данных
        entityManager.persist(employee1);
        entityManager.persist(employee2);
        entityManager.flush();

        // Получаем все Employee из базы данных
        List<Employee> allEmployees = employeeRepository.findAll();

        // Проверяем, что список содержит оба объекта Employee
        assertEquals(2, allEmployees.size());
        assertTrue(allEmployees.contains(employee1));
        assertTrue(allEmployees.contains(employee2));
    }

    @Test
    public void testDeleteEmployee() {
        // Создаем объект Employee
        Employee employee = new Employee();
        employee.setEmployeeName("James Brown");
        employee.setPassword("pass456");
        employee.setPosition("Warehouse Worker");
        employee.setBlocked(false);

        // Сохраняем Employee в базу данных
        entityManager.persist(employee);
        entityManager.flush();

        // Удаляем Employee из базы данных
        employeeRepository.deleteById(employee.getEmployeeID());

        // Пытаемся найти удаленного Employee по его идентификатору
        Optional<Employee> deletedEmployee = employeeRepository.findById(employee.getEmployeeID());

        // Проверяем, что Employee не найден после удаления
        assertFalse(deletedEmployee.isPresent());
    }
}
