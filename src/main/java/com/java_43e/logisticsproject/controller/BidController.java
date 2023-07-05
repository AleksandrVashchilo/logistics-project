package com.java_43e.logisticsproject.controller;

import com.java_43e.logisticsproject.entity.Bid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import com.java_43e.logisticsproject.service.database.BidDatabaseService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BidController {

    private final BidDatabaseService bidDatabaseService;

    @PostMapping(value = "/addBid")
    public Bid addNewBid(@RequestBody Bid bid) {
        bidDatabaseService.addBid(bid);
        return bid;
    }
}

