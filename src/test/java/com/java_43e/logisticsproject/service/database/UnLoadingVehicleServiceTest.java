package com.java_43e.logisticsproject.service.database;
import com.java_43e.logisticsproject.entity.Cargo;
import com.java_43e.logisticsproject.entity.Order;
import com.java_43e.logisticsproject.entity.Vehicle;
import com.java_43e.logisticsproject.repository.CargoRepository;
import com.java_43e.logisticsproject.repository.OrderRepository;
import com.java_43e.logisticsproject.repository.VehicleRepository;
import com.java_43e.logisticsproject.service.impl.UnLoadingVehicleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class UnLoadingVehicleServiceTest {

    private UnLoadingVehicleService unLoadingVehicleService;
    private OrderRepository orderRepository;
    private CargoRepository cargoRepository;
    private VehicleRepository vehicleRepository;

    @BeforeEach
    public void setup() {
        orderRepository = mock(OrderRepository.class);
        cargoRepository = mock(CargoRepository.class);
        vehicleRepository = mock(VehicleRepository.class);
        unLoadingVehicleService = new UnLoadingVehicleServiceImpl(cargoRepository, orderRepository, vehicleRepository);
    }

    @Test
    public void testUnloadVehicle_Success() {
        // Создаем тестовые данные
        Integer orderId = 1;
        Order order = new Order();
        order.setOrderId(orderId);
        order.setClosed(false);

        Integer cargoId = 100;
        Cargo cargo = new Cargo();
        cargo.setCargoId(cargoId);
        cargo.setVolume(50);

        Integer vehicleId = 200;
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId(vehicleId);
        vehicle.setVolumeCurrent(100);
        vehicle.setVolumeMax(200);

        // Устанавливаем моки и поведение репозиториев
        when(orderRepository.findById(orderId)).thenReturn(java.util.Optional.of(order));
        when(cargoRepository.findById(orderId)).thenReturn(java.util.Optional.of(cargo));
        when(vehicleRepository.findById(vehicleId)).thenReturn(java.util.Optional.of(vehicle));

        // Выполняем тестируемый метод
        unLoadingVehicleService.unloadVehicle(orderId);

        // Проверяем, что методы репозиториев были вызваны с правильными параметрами
        verify(orderRepository, times(1)).findById(orderId);
        verify(cargoRepository, times(1)).findById(orderId);
        verify(vehicleRepository, times(1)).findById(vehicleId);

        // Проверяем, что данные обновлены
        assertTrue(order.isClosed());
        assertTrue(cargo.isAccepted());
        assertEquals(50, vehicle.getVolumeCurrent());
    }

    @Test
    public void testUnloadVehicle_InvalidVolume() {
        // Создаем тестовые данные
        Integer orderId = 1;
        Order order = new Order();
        order.setOrderId(orderId);
        order.setClosed(false);

        Integer cargoId = 100;
        Cargo cargo = new Cargo();
        cargo.setCargoId(cargoId);
        cargo.setVolume(200); // Значение объема груза больше максимального объема транспортного средства

        Integer vehicleId = 200;
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId(vehicleId);
        vehicle.setVolumeCurrent(100);
        vehicle.setVolumeMax(150); // Максимальный объем транспортного средства

        // Устанавливаем моки и поведение репозиториев
        when(orderRepository.findById(orderId)).thenReturn(java.util.Optional.of(order));
        when(cargoRepository.findById(orderId)).thenReturn(java.util.Optional.of(cargo));
        when(vehicleRepository.findById(vehicleId)).thenReturn(java.util.Optional.of(vehicle));

        // Выполняем тестируемый метод и проверяем, что он выбрасывает исключение
        assertThrows(IllegalStateException.class, () -> unLoadingVehicleService.unloadVehicle(orderId));

        // Проверяем, что методы репозиториев были вызваны с правильными параметрами
        verify(orderRepository, times(1)).findById(orderId);
        verify(cargoRepository, times(1)).findById(orderId);
        verify(vehicleRepository, times(1)).findById(vehicleId);

        // Проверяем, что данные НЕ обновлены
        assertFalse(order.isClosed());
        assertFalse(cargo.isAccepted());
        assertEquals(100, vehicle.getVolumeCurrent());
    }
}
