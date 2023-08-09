package com.java_43e.logisticsproject.service.impl;
import com.java_43e.logisticsproject.entity.Cargo;
import com.java_43e.logisticsproject.entity.Order;
import com.java_43e.logisticsproject.entity.Vehicle;
import com.java_43e.logisticsproject.repository.CargoRepository;
import com.java_43e.logisticsproject.repository.OrderRepository;
import com.java_43e.logisticsproject.repository.VehicleRepository;
import com.java_43e.logisticsproject.service.database.UnLoadingVehicleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class UnLoadingVehicleServiceImplTest {

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
    public void testUnloadVehicle_SuccessfulUnload() {
        Integer orderId = 1;

        // Create Order
        Order order = new Order();
        order.setOrderId(orderId);
        order.setClosed(false);

        // Create Cargo
        Cargo cargo = new Cargo();
        cargo.setVolume(50);
        cargo.setWeight(100);
        cargo.setPlacePallet(2);

        // Create Vehicle
        Vehicle vehicle = new Vehicle();
        vehicle.setVolumeCurrent(100);
        vehicle.setWeightCurrent(200);
        vehicle.setPlacePalletCurrent(4);

        when(orderRepository.findById(orderId)).thenReturn(java.util.Optional.of(order));
        when(cargoRepository.findById(order.getOrderId())).thenReturn(java.util.Optional.of(cargo));
        when(vehicleRepository.findById(order.getVehicleId())).thenReturn(java.util.Optional.of(vehicle));

        unLoadingVehicleService.unloadVehicle(orderId);

        // Check if the order is closed
        assertTrue(order.isClosed());

        // Check if vehicle's volume, weight, and placePallet are updated correctly
        assertEquals(50, vehicle.getVolumeCurrent());
        assertEquals(100, vehicle.getWeightCurrent());
        assertEquals(2, vehicle.getPlacePalletCurrent());

        // Verify that the repositories' save methods were called
        verify(orderRepository, times(1)).save(order);
        verify(vehicleRepository, times(1)).save(vehicle);
    }

    @Test
    public void testUnloadVehicle_NegativeVolume() {
        Integer orderId = 1;

        // Create Order
        Order order = new Order();
        order.setOrderId(orderId);
        order.setClosed(false);

        // Create Cargo
        Cargo cargo = new Cargo();
        cargo.setVolume(200); // Volume greater than vehicle's current volume

        // Create Vehicle
        Vehicle vehicle = new Vehicle();
        vehicle.setVolumeCurrent(100);

        when(orderRepository.findById(orderId)).thenReturn(java.util.Optional.of(order));
        when(cargoRepository.findById(order.getOrderId())).thenReturn(java.util.Optional.of(cargo));
        when(vehicleRepository.findById(order.getVehicleId())).thenReturn(java.util.Optional.of(vehicle));

        assertThrows(IllegalStateException.class, () -> unLoadingVehicleService.unloadVehicle(orderId));

        // Check if the order is still not closed
        assertFalse(order.isClosed());

        // Check if the vehicle's volume remains the same
        assertEquals(100, vehicle.getVolumeCurrent());

        // Verify that the repositories' save methods were not called
        verify(orderRepository, never()).save(order);
        verify(vehicleRepository, never()).save(vehicle);
    }
}
