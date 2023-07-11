package com.java_43e.logisticsproject.service.impl;

import com.java_43e.logisticsproject.entity.Bid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.java_43e.logisticsproject.repository.BidRepository;
import com.java_43e.logisticsproject.service.database.BidDatabaseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BidDatabaseServiceImpl implements BidDatabaseService {

    private final BidRepository bidRepository;

    @Value(value = "${reference.cost.1km")
    private int referenceCost1Km;
    @Value(value = "${reference.dot.class")
    private int referenceDotClass;
    @Value(value = "${reference.temperature")
    private boolean referenceTemperature;
    @Value(value = "${reference.weight")
    private int referenceWeight;
    @Value(value = "${reference.volume")
    private int referenceVolume;
    @Value(value = "${reference.place.pallet")
    private int referencePlacePallet;

    @Override
    public void addBid(Bid bid) {
        bidRepository.save(bid);
    }

    @Override
    public Optional<Bid> findById(Integer id) {
        Optional bid = bidRepository.findById(id);
        return bid;
    }

    @Override
    public String checkBid(Integer id) {
        Optional<Bid> optionalBid = bidRepository.findById(id);

        if (optionalBid.isPresent()) {
            Bid bid = optionalBid.get();
            int distance = bid.getDistance();
            int price = bid.getPrice();

            double cost1Km = (double) distance / price;
            int referenceCost1KmInt = Integer.parseInt(referenceCost1Km);

            if (cost1Km > referenceCost1KmInt) {
                return "Все плохо";
            } else {
                return "Все хорошо";
            }
        } else {
            return "Запись с указанным id не найдена";
        }
    }
}