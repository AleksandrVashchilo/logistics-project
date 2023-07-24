package com.java_43e.logisticsproject.controller;

import com.java_43e.logisticsproject.service.database.LoadingVehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LoadingVehicleController {

    private final LoadingVehicleService loadingVehicleService;

    @PostMapping("/load-vehicle/{orderId}")
    public ResponseEntity<String> loadVehicle(@PathVariable Integer orderId) {
        try {
            loadingVehicleService.loadVehicle(orderId);
            return ResponseEntity.ok("Vehicle loaded successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to load vehicle: " + e.getMessage());
        }
    }
}
