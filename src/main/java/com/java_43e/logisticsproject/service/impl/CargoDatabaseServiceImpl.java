package com.java_43e.logisticsproject.service.impl;

import com.java_43e.logisticsproject.entity.Cargo;
import com.java_43e.logisticsproject.repository.CargoRepository;
import com.java_43e.logisticsproject.service.database.CargoDatabaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CargoDatabaseServiceImpl implements CargoDatabaseService {

    private final CargoRepository cargoRepository;
    @Override
    public List<Cargo> getCargo() {
        return cargoRepository.findAll();
    }

    @Override
    public Cargo findById(Integer id) {
        return cargoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cargo with id: " + id + " not found"));
    }

    @Override
    public Cargo saveOrUpdateCargo(Cargo cargo) {

        cargoRepository.save(cargo);

        return cargo;
    }
}
