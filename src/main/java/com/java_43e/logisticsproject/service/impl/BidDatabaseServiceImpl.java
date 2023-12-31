package com.java_43e.logisticsproject.service.impl;

import com.java_43e.logisticsproject.entity.Bid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.java_43e.logisticsproject.repository.BidRepository;
import com.java_43e.logisticsproject.service.database.BidDatabaseService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BidDatabaseServiceImpl implements BidDatabaseService {

    private final BidRepository bidRepository;

    @Value(value = "${reference.cost.1km}")
    private BigDecimal referenceCost1Km;
    @Value(value = "${reference.dot.class}")
    private int referenceDotClass;
    @Value(value = "${reference.temperature}")
    private boolean referenceTemperature;
    @Value(value = "${reference.weight}")
    private int referenceWeight;
    @Value(value = "${reference.volume}")
    private int referenceVolume;
    @Value(value = "${reference.place.pallet}")
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
    public Boolean checkBid1Km(Integer id) {
        Optional<Bid> optionalBid = bidRepository.findById(id);

        if (optionalBid.isPresent()) {
            Bid bid = optionalBid.get();
            int distance = bid.getDistance();
            int price = bid.getPrice();

            BigDecimal cost1Km = BigDecimal.valueOf(price)
                    .divide(BigDecimal.valueOf(distance), 2, RoundingMode.HALF_UP);

            return cost1Km.compareTo(referenceCost1Km) > 0;
        } else {
            throw new IllegalArgumentException("Запись с указанным id не найдена");
        }
    }

    @Override
    public Boolean checkBidDot(Integer id) {
        Optional<Bid> optionalBid = bidRepository.findById(id);

        if (optionalBid.isPresent()) {
            Bid bid = optionalBid.get();
            int dotClass = bid.getDotClass();
            return dotClass <= referenceDotClass;
        } else {
            throw new IllegalArgumentException("Запись с указанным id не найдена");
        }
    }

    @Override
    public Boolean checkBidTemperature(Integer id) {
        Optional<Bid> optionalBid = bidRepository.findById(id);

        if (optionalBid.isPresent()) {
            Bid bid = optionalBid.get();
            boolean temperature = bid.isTemperature();
            return temperature == referenceTemperature;
        } else {
            throw new IllegalArgumentException("Запись с указанным id не найдена");
        }
    }

    @Override
    public Boolean checkBidWeight(Integer id) {
        Optional<Bid> optionalBid = bidRepository.findById(id);

        if (optionalBid.isPresent()) {
            Bid bid = optionalBid.get();
            int weight = bid.getWeight();
            return weight <= referenceWeight;
        } else {
            throw new IllegalArgumentException("Запись с указанным id не найдена");
        }
    }

    @Override
    public Boolean checkBidVolume(Integer id) {
        Optional<Bid> optionalBid = bidRepository.findById(id);

        if (optionalBid.isPresent()) {
            Bid bid = optionalBid.get();
            int volume = bid.getVolume();
            return volume <= referenceVolume;
        } else {
            throw new IllegalArgumentException("Запись с указанным id не найдена");
        }
    }

    @Override
    public Boolean checkBidPlacePallet(Integer id) {
        Optional<Bid> optionalBid = bidRepository.findById(id);

        if (optionalBid.isPresent()) {
            Bid bid = optionalBid.get();
            int placePallet = bid.getPlacePallet();
            return placePallet <= referencePlacePallet;
        } else {
            throw new IllegalArgumentException("Запись с указанным id не найдена");
        }
    }
}