package com.java_43e.logisticsproject.repository;
import com.java_43e.logisticsproject.entity.Order;
import com.java_43e.logisticsproject.entity.Cargo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@DataJpaTest
public class ReportRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ReportRepository reportRepository;

    @Test
    public void testFindOpenOrdersWithCargos() {
        // Создаем объект Order
        Order order = new Order();
        order.setClosed(false);
        entityManager.persist(order);

        // Создаем объект Cargo и связываем его с Order
        Cargo cargo = new Cargo();
        cargo.setOrderId(order.getOrderId());
        entityManager.persist(cargo);

        entityManager.flush();

        // Выполняем запрос для поиска открытых заказов с грузами
        List<Object[]> result = reportRepository.findOpenOrdersWithCargos();

        // Проверяем, что результат содержит ожидаемые объекты Order и Cargo
        assertEquals(1, result.size());
        Object[] row = result.get(0);
        Order foundOrder = (Order) row[0];
        Cargo foundCargo = (Cargo) row[1];

        assertEquals(order.getOrderId(), foundOrder.getOrderId());
        assertEquals(cargo.getCargoId(), foundCargo.getCargoId());
    }
}
