package com.java_43e.logisticsproject.service.database;

public interface LoadingVehicleService {
    /**
     * Order search by orderId
     * If the order is closed, an exception is thrown
     * Search for cargo by orderId from the "cargos" table
     * Assignment "boolean isAccepted" in table "cargos"
     * Check if the maximum cargo volume for a given vehicle will be exceeded
     * Update "Integer volumeCurrent" in "vehicles" table
     * Update "Integer weightCurrent" in "vehicles" table
     * Update "Integer placePalletCurrent" in "vehicles" table
     * Save changes to the "vehicles" table
     *
     * @param orderId
     */
    void loadVehicle(Integer orderId);
}
