package com.java_43e.logisticsproject.repository;

import com.java_43e.logisticsproject.entity.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepository extends JpaRepository<Cargo,Integer> {

}
