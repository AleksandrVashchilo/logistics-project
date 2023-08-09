package com.java_43e.logisticsproject.service.database;

import com.java_43e.logisticsproject.entity.Vehicle;

import java.util.List;

public interface VehicleDatabaseService {
    /**
     * get all vehicles
     *
     * @return
     */
    List<Vehicle> getVehicle();

    /**
     * @param id
     * @return finding vehicle by id
     */
    Vehicle findById(Integer id);

    /**
     * vehicle save, update
     *
     * @param vehicle
     */
    void saveOrUpdateVehicle(Vehicle vehicle);
}
