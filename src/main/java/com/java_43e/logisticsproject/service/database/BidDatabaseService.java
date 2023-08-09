package com.java_43e.logisticsproject.service.database;

import com.java_43e.logisticsproject.entity.Bid;

import java.util.Optional;

public interface BidDatabaseService {
    /**
     * bid add, save
     *
     * @param bid
     */
    void addBid(Bid bid);

    /**
     * bid find
     *
     * @param id
     * @return bid
     */
    Optional<Bid> findById(Integer id);

    /**
     * comparison of the cost of 1 kilometer with the reference
     *
     * @param id
     * @return
     */
    Boolean checkBid1Km(Integer id);

    /**
     * bid check DOT class
     *
     * @param id
     * @return
     */
    Boolean checkBidDot(Integer id);

    /**
     * bid check temperature
     *
     * @param id
     * @return
     */
    Boolean checkBidTemperature(Integer id);

    /**
     * bid check weight
     *
     * @param id
     * @return
     */
    Boolean checkBidWeight(Integer id);

    /**
     * bid check volume
     *
     * @param id
     * @return
     */
    Boolean checkBidVolume(Integer id);

    /**
     * bid check pallet place
     *
     * @param id
     * @return
     */
    Boolean checkBidPlacePallet(Integer id);
}
