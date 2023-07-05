package com.java_43e.logisticsproject.service.impl;

import com.java_43e.logisticsproject.entity.Bid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.java_43e.logisticsproject.repository.BidRepository;
import com.java_43e.logisticsproject.service.database.BidDatabaseService;

@Service
@RequiredArgsConstructor
public class BidDatabaseServiceImpl implements BidDatabaseService {

    private final BidRepository bidRepository;

    @Override
    public void addBid(Bid bid) {
        bidRepository.save(bid);
    }
}