package com.java_43e.logisticsproject.controller;

import com.java_43e.logisticsproject.entity.Cargo;
import com.java_43e.logisticsproject.service.database.CargoDatabaseService;
import com.java_43e.logisticsproject.service.impl.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cargo")
@RequiredArgsConstructor
public class CargoController {

    private final CargoDatabaseService cargoDatabaseService;

    @GetMapping
    public List<Cargo> getAllCargos() {

        return cargoDatabaseService.getCargo();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cargo> getCargoById(@PathVariable Integer id) {
        try {
            Cargo cargo = cargoDatabaseService.findById(id);
            return ResponseEntity.ok(cargo);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Cargo> addCargo(@RequestBody Cargo cargo) {
        cargoDatabaseService.saveOrUpdateCargo(cargo);
        return ResponseEntity.ok(cargo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cargo> updateCargo(@PathVariable Integer id, @RequestBody Cargo cargo) {
        try {
            cargoDatabaseService.findById(id);
            cargoDatabaseService.saveOrUpdateCargo(cargo);
            return ResponseEntity.ok(cargo);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
