package com.java_43e.logisticsproject.repository;
import com.java_43e.logisticsproject.entity.Bid;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
public class BidRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BidRepository bidRepository;

    @Test
    public void testSaveBid() {
        // Создаем объект Bid
        Bid bid = new Bid();
        bid.setPrice(1000);
        bid.setDistance(200);
        bid.setDotClass(3);
        bid.setTemperature(true);
        bid.setWeight(500);
        bid.setVolume(100);
        bid.setPlacePallet(5);

        // Сохраняем Bid в базу данных
        Bid savedBid = bidRepository.save(bid);

        // Проверяем, что объект успешно сохранен и имеет ненулевой идентификатор
        assertNotNull(savedBid.getBidId());
    }

    @Test
    public void testFindBidById() {
        // Создаем объект Bid
        Bid bid = new Bid();
        bid.setPrice(1500);
        bid.setDistance(300);
        bid.setDotClass(2);
        bid.setTemperature(false);
        bid.setWeight(400);
        bid.setVolume(80);
        bid.setPlacePallet(4);

        // Сохраняем Bid в базу данных
        entityManager.persist(bid);
        entityManager.flush();

        // Ищем Bid по его идентификатору
        Optional<Bid> foundBid = bidRepository.findById(bid.getBidId());

        // Проверяем, что Bid найден и его значение соответствует ожидаемому
        assertTrue(foundBid.isPresent());
        assertEquals(bid.getPrice(), foundBid.get().getPrice());
    }

    @Test
    public void testFindAllBids() {
        // Создаем объекты Bid
        Bid bid1 = new Bid();
        bid1.setPrice(2000);
        bid1.setDistance(250);
        bid1.setDotClass(3);
        bid1.setTemperature(true);
        bid1.setWeight(600);
        bid1.setVolume(120);
        bid1.setPlacePallet(6);

        Bid bid2 = new Bid();
        bid2.setPrice(1800);
        bid2.setDistance(220);
        bid2.setDotClass(2);
        bid2.setTemperature(false);
        bid2.setWeight(450);
        bid2.setVolume(90);
        bid2.setPlacePallet(5);

        // Сохраняем Bid в базу данных
        entityManager.persist(bid1);
        entityManager.persist(bid2);
        entityManager.flush();

        // Получаем все Bid из базы данных
        List<Bid> allBids = bidRepository.findAll();

        // Проверяем, что список содержит оба объекта Bid
        assertEquals(2, allBids.size());
        assertTrue(allBids.contains(bid1));
        assertTrue(allBids.contains(bid2));
    }

    @Test
    public void testDeleteBid() {
        // Создаем объект Bid
        Bid bid = new Bid();
        bid.setPrice(3000);
        bid.setDistance(180);
        bid.setDotClass(1);
        bid.setTemperature(false);
        bid.setWeight(550);
        bid.setVolume(110);
        bid.setPlacePallet(4);

        // Сохраняем Bid в базу данных
        entityManager.persist(bid);
        entityManager.flush();

        // Удаляем Bid из базы данных
        bidRepository.deleteById(bid.getBidId());

        // Пытаемся найти удаленный Bid по его идентификатору
        Optional<Bid> deletedBid = bidRepository.findById(bid.getBidId());

        // Проверяем, что Bid не найден после удаления
        assertFalse(deletedBid.isPresent());
    }
}
