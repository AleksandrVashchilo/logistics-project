package com.java_43e.logisticsproject.repository;
import com.java_43e.logisticsproject.entity.Vehicle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class VehicleRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Test
    public void testSaveVehicle() {
        // Создаем объект Vehicle
        Vehicle vehicle = new Vehicle();
        vehicle.setDriverName("John Doe");
        vehicle.setAutoNumber("ABC123");
        vehicle.setWeightMax(5000);
        vehicle.setVolumeMax(100);
        vehicle.setPlacePalletMax(5);
        vehicle.setBlocked(false);

        // Сохраняем объект в базу данных
        vehicle = vehicleRepository.save(vehicle);

        // Проверяем, что объект успешно сохранен и получает уникальный идентификатор
        assertNotNull(vehicle.getVehicleId());
    }

    @Test
    public void testFindVehicleById() {
        // Создаем объект Vehicle и сохраняем его в базе данных
        Vehicle vehicle = new Vehicle();
        vehicle.setDriverName("John Doe");
        vehicle.setAutoNumber("ABC123");
        vehicle.setWeightMax(5000);
        vehicle.setVolumeMax(100);
        vehicle.setPlacePalletMax(5);
        vehicle.setBlocked(false);
        vehicle = entityManager.persist(vehicle);
        entityManager.flush();

        // Выполняем запрос для поиска Vehicle по его идентификатору
        Vehicle foundVehicle = vehicleRepository.findById(vehicle.getVehicleId()).orElse(null);

        // Проверяем, что найденный Vehicle не является null и что его идентификатор совпадает с ожидаемым
        assertNotNull(foundVehicle);
        assertEquals(vehicle.getVehicleId(), foundVehicle.getVehicleId());
    }
}
