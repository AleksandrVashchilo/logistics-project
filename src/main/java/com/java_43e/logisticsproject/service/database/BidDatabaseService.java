package com.java_43e.logisticsproject.service.database;

import com.java_43e.logisticsproject.entity.Bid;

import java.util.Optional;

public interface BidDatabaseService {

    void addBid(Bid bid);

    Optional<Bid> findById(Integer id);

    String checkBid(Integer id);
}
