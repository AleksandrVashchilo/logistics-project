package com.java_43e.logisticsproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "vehicles")
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
    private Integer vehicleId;

    @Column(name = "driver_name", length = 50)
    private String driverName;

    @Column(name = "phone", length = 20)
    private Integer phone;

    @Column(name = "auto_number")
    private Integer autoNumber;

    @Column(name = "weight_max")
    private Integer weightMax;

    @Column(name = "volume_max")
    private Integer volumeMax;

    @Column(name = "place_pallet_max")
    private Integer placePalletMax;

    @Column(name = "is_blocked")
    private boolean isBlocked;
}
