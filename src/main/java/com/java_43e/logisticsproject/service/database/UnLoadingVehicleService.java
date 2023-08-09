package com.java_43e.logisticsproject.service.database;

public interface UnLoadingVehicleService {
    /**
     * Order search by orderId
     * Assigning "boolean isClosed" to "orders" table
     * Finding cargo by orderId from the "cargos" table
     * Checking if the cargo volume will become negative
     * Update "Integer volumeCurrent" in "vehicles" table
     * Update "Integer weightCurrent" in "vehicles" table
     * Update "Integer placePalletCurrent" in "vehicles" table
     * Save changes to the "vehicles" table
     *
     * @param orderId
     */
    void unloadVehicle(Integer orderId);
}
