package com.java_43e.logisticsproject.service.database;

import com.java_43e.logisticsproject.entity.Bid;

import java.util.Optional;

public interface BidDatabaseService {

    void addBid(Bid bid);

    /**
     *
     * @param id
     * @return
     */
    Optional<Bid> findById(Integer id);

    Boolean checkBid1Km(Integer id);

    Boolean checkBidDot(Integer id);

    Boolean checkBidTemperature(Integer id);

    Boolean checkBidWeight(Integer id);

    Boolean checkBidVolume(Integer id);

    Boolean checkBidPlacePallet(Integer id);
}
