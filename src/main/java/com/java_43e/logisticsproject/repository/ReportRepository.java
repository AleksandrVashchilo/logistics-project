package com.java_43e.logisticsproject.repository;

import com.java_43e.logisticsproject.entity.Order;
import com.java_43e.logisticsproject.entity.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Order, Integer> {

    @Query("SELECT o, c FROM Order o JOIN Cargo c ON o.orderId = c.orderId WHERE o.isClosed = false")
    List<Object[]> findOpenOrdersWithCargos();
    }

