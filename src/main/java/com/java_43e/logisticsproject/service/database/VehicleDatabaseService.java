package com.java_43e.logisticsproject.service.database;

import com.java_43e.logisticsproject.entity.Vehicle;

import java.util.List;

public interface VehicleDatabaseService {

    List<Vehicle> getVehicle();
    Vehicle findById(Integer id);

    void saveOrUpdateVehicle(Vehicle vehicle);
}
