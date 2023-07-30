package com.java_43e.logisticsproject.repository;
import com.java_43e.logisticsproject.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testSaveCustomer() {
        // Создаем объект Customer
        Customer customer = new Customer();
        customer.setCustomerName("John Doe");
        customer.setContactName("Jane Doe");
        customer.setAddress("123 Main Street");
        customer.setCountry("USA");
        customer.setBlocked(false);

        // Сохраняем Customer в базу данных
        Customer savedCustomer = customerRepository.save(customer);

        // Проверяем, что объект успешно сохранен и имеет ненулевой идентификатор
        assertNotNull(savedCustomer.getCustomerId());
    }

    @Test
    public void testFindCustomerById() {
        // Создаем объект Customer
        Customer customer = new Customer();
        customer.setCustomerName("Alice Smith");
        customer.setContactName("Bob Smith");
        customer.setAddress("456 Elm Street");
        customer.setCountry("Canada");
        customer.setBlocked(true);

        // Сохраняем Customer в базу данных
        entityManager.persist(customer);
        entityManager.flush();

        // Ищем Customer по его идентификатору
        Optional<Customer> foundCustomer = customerRepository.findById(customer.getCustomerId());

        // Проверяем, что Customer найден и его значение соответствует ожидаемому
        assertTrue(foundCustomer.isPresent());
        assertEquals(customer.getCustomerName(), foundCustomer.get().getCustomerName());
    }

    @Test
    public void testFindAllCustomers() {
        // Создаем объекты Customer
        Customer customer1 = new Customer();
        customer1.setCustomerName("Mary Johnson");
        customer1.setContactName("Michael Johnson");
        customer1.setAddress("789 Oak Street");
        customer1.setCountry("UK");
        customer1.setBlocked(false);

        Customer customer2 = new Customer();
        customer2.setCustomerName("Emma Lee");
        customer2.setContactName("David Lee");
        customer2.setAddress("101 Maple Street");
        customer2.setCountry("Australia");
        customer2.setBlocked(true);

        // Сохраняем Customer в базу данных
        entityManager.persist(customer1);
        entityManager.persist(customer2);
        entityManager.flush();

        // Получаем все Customer из базы данных
        List<Customer> allCustomers = customerRepository.findAll();

        // Проверяем, что список содержит оба объекта Customer
        assertEquals(2, allCustomers.size());
        assertTrue(allCustomers.contains(customer1));
        assertTrue(allCustomers.contains(customer2));
    }

    @Test
    public void testDeleteCustomer() {
        // Создаем объект Customer
        Customer customer = new Customer();
        customer.setCustomerName("James Brown");
        customer.setContactName("Linda Brown");
        customer.setAddress("222 Pine Street");
        customer.setCountry("France");
        customer.setBlocked(false);

        // Сохраняем Customer в базу данных
        entityManager.persist(customer);
        entityManager.flush();

        // Удаляем Customer из базы данных
        customerRepository.deleteById(customer.getCustomerId());

        // Пытаемся найти удаленный Customer по его идентификатору
        Optional<Customer> deletedCustomer = customerRepository.findById(customer.getCustomerId());

        // Проверяем, что Customer не найден после удаления
        assertFalse(deletedCustomer.isPresent());
    }
}
