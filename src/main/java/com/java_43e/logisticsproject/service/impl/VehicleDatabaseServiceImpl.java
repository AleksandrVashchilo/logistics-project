package com.java_43e.logisticsproject.service.impl;

import com.java_43e.logisticsproject.entity.Vehicle;
import com.java_43e.logisticsproject.repository.VehicleRepository;
import com.java_43e.logisticsproject.service.database.VehicleDatabaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class VehicleDatabaseServiceImpl implements VehicleDatabaseService {

    private final VehicleRepository vehicleRepository;

    @Override
    public List<Vehicle> getVehicle() {
        return vehicleRepository.findAll();
    }

    @Override
    public Vehicle findById(Integer id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle with id: " + id + " not found"));
    }

    @Override
    public void saveOrUpdateVehicle(Vehicle vehicle) {

        vehicleRepository.save(vehicle);

    }
}
