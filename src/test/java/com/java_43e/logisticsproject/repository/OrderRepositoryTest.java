package com.java_43e.logisticsproject.repository;

import com.java_43e.logisticsproject.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
public class OrderRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void testSaveOrder() {
        // Создаем объект Order
        Order order = new Order();
        order.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        order.setCustomerId(1);
        order.setEmployeeID(2);
        order.setVehicleId(3);
        order.setClosed(false);
        order.setDeleted(false);

        // Сохраняем Order в базу данных
        Order savedOrder = orderRepository.save(order);

        // Проверяем, что объект успешно сохранен и имеет ненулевой идентификатор
        assertNotNull(savedOrder.getOrderId());
    }

    @Test
    public void testFindOrderById() {
        // Создаем объект Order
        Order order = new Order();
        order.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        order.setCustomerId(1);
        order.setEmployeeID(2);
        order.setVehicleId(3);
        order.setClosed(false);
        order.setDeleted(false);

        // Сохраняем Order в базу данных
        entityManager.persist(order);
        entityManager.flush();

        // Ищем Order по его идентификатору
        Optional<Order> foundOrder = orderRepository.findById(order.getOrderId());

        // Проверяем, что Order найден и его значение соответствует ожидаемому
        assertTrue(foundOrder.isPresent());
        assertEquals(order.getCreatedAt(), foundOrder.get().getCreatedAt());
    }

    @Test
    public void testFindAllOrders() {
        // Создаем объекты Order
        Order order1 = new Order();
        order1.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        order1.setCustomerId(1);
        order1.setEmployeeID(2);
        order1.setVehicleId(3);
        order1.setClosed(false);
        order1.setDeleted(false);

        Order order2 = new Order();
        order2.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        order2.setCustomerId(4);
        order2.setEmployeeID(5);
        order2.setVehicleId(6);
        order2.setClosed(true);
        order2.setDeleted(false);

        // Сохраняем Order в базу данных
        entityManager.persist(order1);
        entityManager.persist(order2);
        entityManager.flush();

        // Получаем все Order из базы данных
        List<Order> allOrders = orderRepository.findAll();

        // Проверяем, что список содержит оба объекта Order
        assertEquals(2, allOrders.size());
        assertTrue(allOrders.contains(order1));
        assertTrue(allOrders.contains(order2));
    }

    @Test
    public void testDeleteOrder() {
        // Создаем объект Order
        Order order = new Order();
        order.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        order.setCustomerId(1);
        order.setEmployeeID(2);
        order.setVehicleId(3);
        order.setClosed(false);
        order.setDeleted(false);

        // Сохраняем Order в базу данных
        entityManager.persist(order);
        entityManager.flush();

        // Удаляем Order из базы данных
        orderRepository.deleteById(order.getOrderId());

        // Пытаемся найти удаленный Order по его идентификатору
        Optional<Order> deletedOrder = orderRepository.findById(order.getOrderId());

        // Проверяем, что Order не найден после удаления
        assertFalse(deletedOrder.isPresent());
    }
}
