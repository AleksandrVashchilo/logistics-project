package com.java_43e.logisticsproject.controller;

import com.java_43e.logisticsproject.entity.Bid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.java_43e.logisticsproject.service.database.BidDatabaseService;

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BidController {

    private final BidDatabaseService bidDatabaseService;

    @PostMapping(value = "/bid-add/")
    public Bid addBid(@RequestBody Bid bid) {
        bidDatabaseService.addBid(bid);
        return bid;
    }

    @GetMapping(value = "/bid-get/{id}")
    public ResponseEntity<?> getBid(@PathVariable(name = "id") Integer id) {
        Optional<Bid> bidOptional = bidDatabaseService.findById(id);
        Bid bid = bidOptional.get();
        //return bidOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
        return bidOptional != null ? ResponseEntity.ok(bid) : ResponseEntity.notFound().build();
    }

    @GetMapping("/bid-check/{id}")
    public ResponseEntity<String> checkBid(@PathVariable Integer id) {

        Boolean result = bidDatabaseService.checkBid(id);
        if (result) {
            return ResponseEntity.ok("Все хорошо");
        } else {
            return ResponseEntity.ok("Все плохо");
        }
    }
}

