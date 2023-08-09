package com.java_43e.logisticsproject.service.impl;
import com.java_43e.logisticsproject.entity.Order;
import com.java_43e.logisticsproject.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class OrderDatabaseServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderDatabaseServiceImpl orderDatabaseService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetOrder() {
        // Создаем список заказов для возврата из репозитория
        List<Order> orderList = new ArrayList<>();
        orderList.add(new Order(1, Timestamp.valueOf("2023-07-30 10:00:00"), 1, 1, 1, false, false));
        orderList.add(new Order(2, Timestamp.valueOf("2023-07-30 11:00:00"), 2, 2, 2, false, false));

        // Настройка поведения mock объекта
        when(orderRepository.findAll()).thenReturn(orderList);

        // Вызов тестируемого метода
        List<Order> result = orderDatabaseService.getOrder();

        // Проверки
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getOrderId());
        assertEquals(2, result.get(1).getOrderId());

        // Проверяем, что метод findAll вызывался один раз
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    public void testFindById_ValidId() {
        // Создаем тестовый заказ с id=1
        Order order = new Order(1, Timestamp.valueOf("2023-07-30 10:00:00"), 1, 1, 1, false, false);

        // Настройка поведения mock объекта
        when(orderRepository.findById(1)).thenReturn(Optional.of(order));

        // Вызов тестируемого метода
        Order result = orderDatabaseService.findById(1);

        // Проверки
        assertNotNull(result);
        assertEquals(1, result.getOrderId());
        assertEquals(1, result.getCustomerId());

        // Проверяем, что метод findById вызывался один раз с аргументом 1
        verify(orderRepository, times(1)).findById(1);
    }

    @Test
    public void testFindById_InvalidId() {
        // Настройка поведения mock объекта
        when(orderRepository.findById(99)).thenReturn(Optional.empty());

        // Вызов тестируемого метода и проверка исключения
        assertThrows(ResourceNotFoundException.class, () -> orderDatabaseService.findById(99));

        // Проверяем, что метод findById вызывался один раз с аргументом 99
        verify(orderRepository, times(1)).findById(99);
    }

    @Test
    public void testSaveOrUpdateOrder() {
        // Создаем тестовый заказ
        Order orderToSave = new Order();
        orderToSave.setCustomerId(1);
        orderToSave.setEmployeeID(1);
        orderToSave.setVehicleId(1);

        // Вызов тестируемого метода
        orderDatabaseService.saveOrUpdateOrder(orderToSave);

        // Проверяем, что метод save вызывался один раз с переданным заказом
        verify(orderRepository, times(1)).save(orderToSave);
    }
}
