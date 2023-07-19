package com.java_43e.logisticsproject.controller;

import com.java_43e.logisticsproject.entity.Vehicle;
import com.java_43e.logisticsproject.service.database.VehicleDatabaseService;
import com.java_43e.logisticsproject.service.impl.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleDatabaseService vehicleDatabaseService;

    @GetMapping
    public List<Vehicle> getAllVehicles() {
        return vehicleDatabaseService.getVehicle();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Integer id) {
        try {
            Vehicle vehicle = vehicleDatabaseService.findById(id);
            return ResponseEntity.ok(vehicle);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Vehicle> addVehicle(@RequestBody Vehicle vehicle) {
        vehicleDatabaseService.saveOrUpdateVehicle(vehicle);
        return ResponseEntity.ok(vehicle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable Integer id, @RequestBody Vehicle vehicle) {
        try {
            vehicleDatabaseService.findById(id);
            vehicleDatabaseService.saveOrUpdateVehicle(vehicle);
            return ResponseEntity.ok(vehicle);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
