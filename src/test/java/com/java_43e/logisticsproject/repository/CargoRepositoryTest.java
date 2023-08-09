package com.java_43e.logisticsproject.repository;
import com.java_43e.logisticsproject.entity.Cargo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
public class CargoRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CargoRepository cargoRepository;

    @Test
    public void testSaveCargo() {
        // Создаем объект Cargo
        Cargo cargo = new Cargo();
        cargo.setOrderId(1);
        cargo.setCargoName("Cargo 1");
        cargo.setDotClass(3);
        cargo.setWeight(500);
        cargo.setVolume(100);
        cargo.setPlacePallet(5);
        cargo.setPrice(2000);
        cargo.setAccepted(true);

        // Сохраняем Cargo в базу данных
        Cargo savedCargo = cargoRepository.save(cargo);

        // Проверяем, что объект успешно сохранен и имеет ненулевой идентификатор
        assertNotNull(savedCargo.getCargoId());
    }

    @Test
    public void testFindCargoById() {
        // Создаем объект Cargo
        Cargo cargo = new Cargo();
        cargo.setOrderId(2);
        cargo.setCargoName("Cargo 2");
        cargo.setDotClass(2);
        cargo.setWeight(400);
        cargo.setVolume(80);
        cargo.setPlacePallet(4);
        cargo.setPrice(1800);
        cargo.setAccepted(false);

        // Сохраняем Cargo в базу данных
        entityManager.persist(cargo);
        entityManager.flush();

        // Ищем Cargo по его идентификатору
        Optional<Cargo> foundCargo = cargoRepository.findById(cargo.getCargoId());

        // Проверяем, что Cargo найден и его значение соответствует ожидаемому
        assertTrue(foundCargo.isPresent());
        assertEquals(cargo.getOrderId(), foundCargo.get().getOrderId());
    }

    @Test
    public void testFindAllCargos() {
        // Создаем объекты Cargo
        Cargo cargo1 = new Cargo();
        cargo1.setOrderId(3);
        cargo1.setCargoName("Cargo 3");
        cargo1.setDotClass(1);
        cargo1.setWeight(600);
        cargo1.setVolume(120);
        cargo1.setPlacePallet(6);
        cargo1.setPrice(2500);
        cargo1.setAccepted(true);

        Cargo cargo2 = new Cargo();
        cargo2.setOrderId(4);
        cargo2.setCargoName("Cargo 4");
        cargo2.setDotClass(3);
        cargo2.setWeight(550);
        cargo2.setVolume(110);
        cargo2.setPlacePallet(5);
        cargo2.setPrice(2200);
        cargo2.setAccepted(false);

        // Сохраняем Cargo в базу данных
        entityManager.persist(cargo1);
        entityManager.persist(cargo2);
        entityManager.flush();

        // Получаем все Cargo из базы данных
        List<Cargo> allCargos = cargoRepository.findAll();

        // Проверяем, что список содержит оба объекта Cargo
        assertEquals(2, allCargos.size());
        assertTrue(allCargos.contains(cargo1));
        assertTrue(allCargos.contains(cargo2));
    }

    @Test
    public void testDeleteCargo() {
        // Создаем объект Cargo
        Cargo cargo = new Cargo();
        cargo.setOrderId(5);
        cargo.setCargoName("Cargo 5");
        cargo.setDotClass(2);
        cargo.setWeight(450);
        cargo.setVolume(90);
        cargo.setPlacePallet(5);
        cargo.setPrice(2000);
        cargo.setAccepted(true);

        // Сохраняем Cargo в базу данных
        entityManager.persist(cargo);
        entityManager.flush();

        // Удаляем Cargo из базы данных
        cargoRepository.deleteById(cargo.getCargoId());

        // Пытаемся найти удаленный Cargo по его идентификатору
        Optional<Cargo> deletedCargo = cargoRepository.findById(cargo.getCargoId());

        // Проверяем, что Cargo не найден после удаления
        assertFalse(deletedCargo.isPresent());
    }
}
