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

    @Column(name = "phone", length = 50)
    private Integer phone;

    @Column(name = "auto_number")
    private String autoNumber;

    @Column(name = "weight_max")
    private Integer weightMax;

    @Column(name = "weight_current")
    private Integer weightCurrent;

    @Column(name = "volume_max")
    private Integer volumeMax;

    @Column(name = "volume_current")
    private Integer volumeCurrent;

    @Column(name = "place_pallet_max")
    private Integer placePalletMax;

    @Column(name = "place_pallet_current")
    private Integer placePalletCurrent;

    @Column(name = "is_blocked")
    private boolean isBlocked;
}
