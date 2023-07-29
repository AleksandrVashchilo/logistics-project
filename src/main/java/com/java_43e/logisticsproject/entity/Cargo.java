package com.java_43e.logisticsproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "cargos")
@AllArgsConstructor
@NoArgsConstructor
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cargo_id")
    private Integer cargoId;

    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "cargo_name", length = 50)
    private String cargoName;

    @Column(name = "dot_class")
    private Integer dotClass;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "volume")
    private Integer volume;

    @Column(name = "place_pallet")
    private Integer placePallet;

    @Column(name = "price")
    private Integer price;

    @Column(name = "is_accepted")
    private boolean isAccepted;
}
