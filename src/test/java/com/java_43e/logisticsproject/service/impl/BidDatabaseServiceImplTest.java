package com.java_43e.logisticsproject.service.impl;
import com.java_43e.logisticsproject.entity.Bid;
import com.java_43e.logisticsproject.repository.BidRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
public class BidDatabaseServiceImplTest {

    @Mock
    private BidRepository bidRepository;

    @InjectMocks
    private BidDatabaseServiceImpl bidDatabaseService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddBid() {
        Bid bid = new Bid();
        // Здесь можно задать необходимые значения для объекта bid

        bidDatabaseService.addBid(bid);

        // Проверка, что метод save() вызывался один раз с объектом bid в качестве аргумента
        Mockito.verify(bidRepository).save(bid);
    }

    @Test
    public void testFindById_ValidId() {
        Bid bid = new Bid();
        // Здесь можно задать необходимые значения для объекта bid
        bid.setBidId(1);

        when(bidRepository.findById(1)).thenReturn(Optional.of(bid));

        Optional<Bid> result = bidDatabaseService.findById(1);

        assertTrue(result.isPresent());
        assertEquals(bid, result.get());
    }

    @Test
    public void testFindById_InvalidId() {
        when(bidRepository.findById(anyInt())).thenReturn(Optional.empty());

        Optional<Bid> result = bidDatabaseService.findById(100);

        assertFalse(result.isPresent());
    }

    @Test
    public void testCheckBid1Km_ValidId() {
        Bid bid = new Bid();
        // Здесь можно задать необходимые значения для объекта bid
        bid.setBidId(1);
        bid.setDistance(100);
        bid.setPrice(500);

        when(bidRepository.findById(1)).thenReturn(Optional.of(bid));

        boolean result = bidDatabaseService.checkBid1Km(1);

        assertTrue(result);
    }

}
