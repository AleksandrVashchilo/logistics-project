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
@RequestMapping("/bid")
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
        return bidOptional != null ? ResponseEntity.ok(bid) : ResponseEntity.notFound().build();
    }

    @GetMapping("/bid-check-1km/{id}")
    public ResponseEntity<String> checkBid1Km(@PathVariable Integer id) {

        Boolean result = bidDatabaseService.checkBid1Km(id);
        if (result) {
            return ResponseEntity.ok("Все хорошо");
        } else {
            return ResponseEntity.ok("Все плохо");
        }
    }

    @GetMapping("/bid-check-dot/{id}")
    public ResponseEntity<String> checkBidDot(@PathVariable Integer id) {

        Boolean result = bidDatabaseService.checkBidDot(id);
        if (result) {
            return ResponseEntity.ok("Все хорошо");
        } else {
            return ResponseEntity.ok("Все плохо");
        }
    }

    @GetMapping("/bid-check-temperature/{id}")
    public ResponseEntity<String> checkBidTemperature(@PathVariable Integer id) {

        Boolean result = bidDatabaseService.checkBidTemperature(id);
        if (result) {
            return ResponseEntity.ok("Все хорошо");
        } else {
            return ResponseEntity.ok("Все плохо");
        }
    }

    @GetMapping("/bid-check-weight/{id}")
    public ResponseEntity<String> checkBidWeight(@PathVariable Integer id) {

        Boolean result = bidDatabaseService.checkBidWeight(id);
        if (result) {
            return ResponseEntity.ok("Все хорошо");
        } else {
            return ResponseEntity.ok("Все плохо");
        }
    }

    @GetMapping("/bid-check-volume/{id}")
    public ResponseEntity<String> checkBidVolume(@PathVariable Integer id) {

        Boolean result = bidDatabaseService.checkBidVolume(id);
        if (result) {
            return ResponseEntity.ok("Все хорошо");
        } else {
            return ResponseEntity.ok("Все плохо");
        }
    }

    @GetMapping("/bid-check-place-pallet/{id}")
    public ResponseEntity<String> checkBidPlacePallet(@PathVariable Integer id) {

        Boolean result = bidDatabaseService.checkBidPlacePallet(id);
        if (result) {
            return ResponseEntity.ok("Все хорошо");
        } else {
            return ResponseEntity.ok("Все плохо");
        }
    }
}

