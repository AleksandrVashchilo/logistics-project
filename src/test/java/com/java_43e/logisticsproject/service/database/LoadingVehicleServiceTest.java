package com.java_43e.logisticsproject.service.database;
import com.java_43e.logisticsproject.entity.Cargo;
import com.java_43e.logisticsproject.entity.Order;
import com.java_43e.logisticsproject.entity.Vehicle;
import com.java_43e.logisticsproject.repository.CargoRepository;
import com.java_43e.logisticsproject.repository.OrderRepository;
import com.java_43e.logisticsproject.repository.VehicleRepository;
import com.java_43e.logisticsproject.service.impl.LoadingVehicleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class LoadingVehicleServiceTest {

    @Mock
    private CargoRepository cargoRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private VehicleRepository vehicleRepository;

    @InjectMocks
    private LoadingVehicleServiceImpl loadingVehicleService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoadVehicle_ValidOrder() {
        // Создаем тестовые данные
        Order order = new Order();
        order.setOrderId(1);
        order.setClosed(false);
        order.setVehicleId(1);

        Cargo cargo = new Cargo();
        cargo.setOrderId(1);
        cargo.setVolume(10);
        cargo.setWeight(100);
        cargo.setPlacePallet(5);

        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId(1);
        vehicle.setVolumeCurrent(50);
        vehicle.setVolumeMax(100);
        vehicle.setWeightCurrent(300);
        vehicle.setWeightMax(500);
        vehicle.setPlacePalletCurrent(10);
        vehicle.setPlacePalletMax(15);

        // Настройка поведения mock объектов
        when(orderRepository.findById(order.getOrderId())).thenReturn(java.util.Optional.of(order));
        when(cargoRepository.findById(order.getOrderId())).thenReturn(java.util.Optional.of(cargo));
        when(vehicleRepository.findById(order.getVehicleId())).thenReturn(java.util.Optional.of(vehicle));

        // Вызов тестируемого метода
        loadingVehicleService.loadVehicle(order.getOrderId());

        // Проверки
        assertTrue(cargo.isAccepted());
        assertEquals(60, vehicle.getVolumeCurrent());
        assertEquals(400, vehicle.getWeightCurrent());
        assertEquals(15, vehicle.getPlacePalletCurrent());

        // Проверяем, что методы сохранения вызывались
        verify(cargoRepository, times(1)).save(cargo);
        verify(vehicleRepository, times(1)).save(vehicle);
    }

    @Test
    public void testLoadVehicle_ClosedOrder() {
        // Создаем тестовые данные
        Order order = new Order();
        order.setOrderId(1);
        order.setClosed(true);

        // Настройка поведения mock объекта
        when(orderRepository.findById(order.getOrderId())).thenReturn(java.util.Optional.of(order));

        // Вызов тестируемого метода и проверка исключения
        assertThrows(IllegalStateException.class, () -> loadingVehicleService.loadVehicle(order.getOrderId()));

        // Проверяем, что методы сохранения не вызывались
        verify(cargoRepository, never()).save(any());
        verify(vehicleRepository, never()).save(any());
    }

    @Test
    public void testLoadVehicle_ExceededVolume() {
        // Создаем тестовые данные
        Order order = new Order();
        order.setOrderId(1);
        order.setClosed(false);
        order.setVehicleId(1);

        Cargo cargo = new Cargo();
        cargo.setOrderId(1);
        cargo.setVolume(60);

        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId(1);
        vehicle.setVolumeCurrent(50);
        vehicle.setVolumeMax(100);

        // Настройка поведения mock объектов
        when(orderRepository.findById(order.getOrderId())).thenReturn(java.util.Optional.of(order));
        when(cargoRepository.findById(order.getOrderId())).thenReturn(java.util.Optional.of(cargo));
        when(vehicleRepository.findById(order.getVehicleId())).thenReturn(java.util.Optional.of(vehicle));

        // Вызов тестируемого метода и проверка исключения
        assertThrows(IllegalStateException.class, () -> loadingVehicleService.loadVehicle(order.getOrderId()));

        // Проверяем, что методы сохранения не вызывались
        verify(cargoRepository, never()).save(any());
        verify(vehicleRepository, never()).save(any());
    }

    @Test
    public void testLoadVehicle_ExceededWeight() {
        // Создаем тестовые данные
        Order order = new Order();
        order.setOrderId(1);
        order.setClosed(false);
        order.setVehicleId(1);

        Cargo cargo = new Cargo();
        cargo.setOrderId(1);
        cargo.setWeight(300);

        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId(1);
        vehicle.setWeightCurrent(300);
        vehicle.setWeightMax(500);

        // Настройка поведения mock объектов
        when(orderRepository.findById(order.getOrderId())).thenReturn(java.util.Optional.of(order));
        when(cargoRepository.findById(order.getOrderId())).thenReturn(java.util.Optional.of(cargo));
        when(vehicleRepository.findById(order.getVehicleId())).thenReturn(java.util.Optional.of(vehicle));

        // Вызов тестируемого метода и проверка исключения
        assertThrows(IllegalStateException.class, () -> loadingVehicleService.loadVehicle(order.getOrderId()));

        // Проверяем, что методы сохранения не вызывались
        verify(cargoRepository, never()).save(any());
        verify(vehicleRepository, never()).save(any());
    }

    @Test
    public void testLoadVehicle_ExceededPlacePallet() {
        // Создаем тестовые данные
        Order order = new Order();
        order.setOrderId(1);
        order.setClosed(false);
        order.setVehicleId(1);

        Cargo cargo = new Cargo();
        cargo.setOrderId(1);
        cargo.setPlacePallet(10);

        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId(1);
        vehicle.setPlacePalletCurrent(10);
        vehicle.setPlacePalletMax(15);

        // Настройка поведения mock объектов
        when(orderRepository.findById(order.getOrderId())).thenReturn(java.util.Optional.of(order));
        when(cargoRepository.findById(order.getOrderId())).thenReturn(java.util.Optional.of(cargo));
        when(vehicleRepository.findById(order.getVehicleId())).thenReturn(java.util.Optional.of(vehicle));

        // Вызов тестируемого метода и проверка исключения
        assertThrows(IllegalStateException.class, () -> loadingVehicleService.loadVehicle(order.getOrderId()));

        // Проверяем, что методы сохранения не вызывались
        verify(cargoRepository, never()).save(any());
        verify(vehicleRepository, never()).save(any());
    }
}
