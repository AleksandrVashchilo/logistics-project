package com.java_43e.logisticsproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "customers")
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "customer_name", length = 50)
    private String customerName;

    @Column(name = "contact_name", length = 50)
    private String contactName;

    @Column(name = "address", length = 80)
    private String address;

    @Column(name = "country", length = 20)
    private String country;

    @Column(name = "is_blocked")
    private boolean isBlocked;
}
