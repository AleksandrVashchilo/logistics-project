package com.java_43e.logisticsproject.service.impl;

import com.java_43e.logisticsproject.entity.Cargo;
import com.java_43e.logisticsproject.entity.Order;
import com.java_43e.logisticsproject.entity.Vehicle;
import com.java_43e.logisticsproject.repository.CargoRepository;
import com.java_43e.logisticsproject.repository.OrderRepository;
import com.java_43e.logisticsproject.repository.VehicleRepository;
import com.java_43e.logisticsproject.service.database.UnLoadingVehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UnLoadingVehicleServiceImpl implements UnLoadingVehicleService {

    private final CargoRepository cargoRepository;
    private final OrderRepository orderRepository;
    private final VehicleRepository vehicleRepository;

    @Override
    @Transactional
    public void unloadVehicle(Integer orderId) {

        // 1. Найти заказ по orderId
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order with id: " + orderId + " not found"));

        // 2. Присвоить "boolean isClosed" в таблице "orders"
        order.setClosed(true);
        orderRepository.save(order);

        // 3. Найти груз по orderId из таблицы "cargos"
        Cargo cargo = cargoRepository.findById(order.getOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Cargo with id: " + order.getOrderId() + " not found"));

        // 4. Проверить, будет ли объем груза становиться отрицательным
        Vehicle vehicle = vehicleRepository.findById(order.getVehicleId())
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle with id: " + order.getVehicleId() + " not found"));

        int newVolumeCurrent = vehicle.getVolumeCurrent() - cargo.getVolume();
        if (newVolumeCurrent < 0) {
            // Выбрасываем исключение и отменяем транзакцию
            throw new IllegalStateException("Vehicle's current volume cannot be negative.");
        }

        // 5. Обновить "Integer volumeCurrent" в таблице "vehicles"
        vehicle.setVolumeCurrent(newVolumeCurrent);
        vehicleRepository.save(vehicle);
    }

}

