package com.java_43e.logisticsproject.service.database;

import com.java_43e.logisticsproject.entity.Cargo;

import java.util.List;

public interface CargoDatabaseService {

    List<Cargo> getCargo();

    Cargo findById(Integer id);

    Cargo saveOrUpdateCargo(Cargo cargo);
}
