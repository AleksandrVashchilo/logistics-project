package com.java_43e.logisticsproject.controller;

import com.java_43e.logisticsproject.service.database.UnLoadingVehicleService;
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
public class UnLoadingVehicleController {

    private final UnLoadingVehicleService unLoadingVehicleService;

    @PostMapping("/upload-vehicle/{orderId}")
    public ResponseEntity<String> unloadVehicle(@PathVariable Integer orderId) {
        try {
            unLoadingVehicleService.unloadVehicle(orderId);
            return ResponseEntity.ok("Vehicle unloaded successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to unload vehicle: " + e.getMessage());
        }
    }
}
