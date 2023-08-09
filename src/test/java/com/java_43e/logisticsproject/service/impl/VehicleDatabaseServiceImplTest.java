package com.java_43e.logisticsproject.service.impl;
import com.java_43e.logisticsproject.entity.Vehicle;
import com.java_43e.logisticsproject.repository.VehicleRepository;
import com.java_43e.logisticsproject.service.database.VehicleDatabaseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class VehicleDatabaseServiceImplTest {

    private VehicleDatabaseService vehicleDatabaseService;
    private VehicleRepository vehicleRepository;

    @BeforeEach
    public void setup() {
        vehicleRepository = mock(VehicleRepository.class);
        vehicleDatabaseService = new VehicleDatabaseServiceImpl(vehicleRepository);
    }

    @Test
    public void testGetVehicle() {
        // Создаем тестовые данные
        Vehicle vehicle1 = new Vehicle();
        vehicle1.setVehicleId(1);
        vehicle1.setDriverName("John Doe");
        vehicle1.setAutoNumber("ABC123");

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setVehicleId(2);
        vehicle2.setDriverName("Jane Smith");
        vehicle2.setAutoNumber("XYZ789");

        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.add(vehicle1);
        vehicleList.add(vehicle2);

        // Устанавливаем моки и поведение репозитория
        when(vehicleRepository.findAll()).thenReturn(vehicleList);

        // Выполняем тестируемый метод
        List<Vehicle> result = vehicleDatabaseService.getVehicle();

        // Проверяем результат
        assertEquals(2, result.size());
        assertEquals(vehicle1.getDriverName(), result.get(0).getDriverName());
        assertEquals(vehicle2.getDriverName(), result.get(1).getDriverName());

        // Проверяем, что метод репозитория был вызван
        verify(vehicleRepository, times(1)).findAll();
    }

    @Test
    public void testFindById_ValidId() {
        // Создаем тестовые данные
        Integer vehicleId = 1;
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId(vehicleId);
        vehicle.setDriverName("John Doe");
        vehicle.setAutoNumber("ABC123");

        // Устанавливаем моки и поведение репозитория
        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.of(vehicle));

        // Выполняем тестируемый метод
        Vehicle result = vehicleDatabaseService.findById(vehicleId);

        // Проверяем результат
        assertNotNull(result);
        assertEquals(vehicle.getDriverName(), result.getDriverName());

        // Проверяем, что метод репозитория был вызван
        verify(vehicleRepository, times(1)).findById(vehicleId);
    }

    @Test
    public void testFindById_InvalidId() {
        // Создаем тестовые данные
        Integer vehicleId = 100;

        // Устанавливаем моки и поведение репозитория
        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.empty());

        // Выполняем тестируемый метод и проверяем, что он выбрасывает исключение
        assertThrows(ResourceNotFoundException.class, () -> vehicleDatabaseService.findById(vehicleId));

        // Проверяем, что метод репозитория был вызван
        verify(vehicleRepository, times(1)).findById(vehicleId);
    }

    @Test
    public void testSaveOrUpdateVehicle() {
        // Создаем тестовые данные
        Vehicle vehicleToSave = new Vehicle();
        vehicleToSave.setDriverName("John Doe");
        vehicleToSave.setAutoNumber("ABC123");

        // Выполняем тестируемый метод
        vehicleDatabaseService.saveOrUpdateVehicle(vehicleToSave);

        // Проверяем, что метод репозитория был вызван с правильными параметрами
        verify(vehicleRepository, times(1)).save(vehicleToSave);
    }
}
