package com.java_43e.logisticsproject.service.impl;

import com.java_43e.logisticsproject.entity.Cargo;
import com.java_43e.logisticsproject.entity.Order;
import com.java_43e.logisticsproject.entity.Vehicle;
import com.java_43e.logisticsproject.repository.CargoRepository;
import com.java_43e.logisticsproject.repository.OrderRepository;
import com.java_43e.logisticsproject.repository.VehicleRepository;
import com.java_43e.logisticsproject.service.database.LoadingVehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoadingVehicleServiceImpl implements LoadingVehicleService {


    private final CargoRepository cargoRepository;
    private final OrderRepository orderRepository;
    private final VehicleRepository vehicleRepository;


    @Override
    @Transactional
    public void loadVehicle(Integer orderId) {
        // 1. Найти заказ по orderId
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order with id: " + orderId + " not found"));

        // 2. Если заказ закрыт, выбросить исключение или выполнить другие действия
        if (order.isClosed()) {
            throw new IllegalStateException("Order is already closed.");
        }

        // 3. Найти груз по orderId из таблицы "cargos"
        Cargo cargo = cargoRepository.findById(order.getOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Cargo with id: " + order.getOrderId() + " not found"));

        // 4. Присвоить "boolean isAccepted" в таблице "cargos"
        cargo.setAccepted(true);
        cargoRepository.save(cargo);

        // 5. Проверить, будет ли превышен максимальный объем груза для данного транспортного средства
        Vehicle vehicle = vehicleRepository.findById(order.getVehicleId())
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle with id: " + order.getVehicleId() + " not found"));

        int newVolumeCurrent = vehicle.getVolumeCurrent() + cargo.getVolume();
        if (newVolumeCurrent > vehicle.getVolumeMax()) {
            // Выбрасываем исключение и отменяем транзакцию
            throw new IllegalStateException("Vehicle's maximum volume exceeded.");
        }

        // 6. Обновить "Integer volumeCurrent" в таблице "vehicles"
        vehicle.setVolumeCurrent(newVolumeCurrent);
        vehicleRepository.save(vehicle);
    }
}
