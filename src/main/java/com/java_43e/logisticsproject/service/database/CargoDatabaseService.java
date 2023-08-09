package com.java_43e.logisticsproject.service.database;

import com.java_43e.logisticsproject.entity.Cargo;

import java.util.List;

public interface CargoDatabaseService {
    /**
     * all cargo get
     *
     * @return
     */
    List<Cargo> getCargo();

    /**
     * cargo find
     *
     * @param id
     * @return
     */
    Cargo findById(Integer id);

    /**
     * cargo save, update
     *
     * @param cargo
     * @return
     */
    Cargo saveOrUpdateCargo(Cargo cargo);
}
