package com.java_43e.logisticsproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "bids")
@AllArgsConstructor
@NoArgsConstructor
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bid_id")
    private Integer bidId;

    @Column(name = "price")
    private Integer price;

    @Column(name = "distance")
    private Integer distance;

    @Column(name = "dot_class")
    private Integer dotClass;

    @Column(name = "temperature")
    private boolean temperature;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "volume")
    private Integer volume;

    @Column(name = "place_pallet")
    private Integer placePallet;
}
