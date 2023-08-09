package com.java_43e.logisticsproject.service.database;
import com.java_43e.logisticsproject.entity.Cargo;
import com.java_43e.logisticsproject.repository.CargoRepository;
import com.java_43e.logisticsproject.service.impl.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
public class CargoDatabaseServiceTest {

    @Mock
    private CargoRepository cargoRepository;

    @InjectMocks
    private CargoDatabaseService cargoDatabaseService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCargo() {
        List<Cargo> mockCargoList = new ArrayList<>();
        mockCargoList.add(new Cargo());
        mockCargoList.add(new Cargo());

        when(cargoRepository.findAll()).thenReturn(mockCargoList);

        List<Cargo> result = cargoDatabaseService.getCargo();

        assertEquals(2, result.size());
    }

    @Test
    public void testFindById_ValidId() {
        Cargo mockCargo = new Cargo();
        mockCargo.setCargoId(1);

        when(cargoRepository.findById(1)).thenReturn(Optional.of(mockCargo));

        Cargo result = cargoDatabaseService.findById(1);

        assertEquals(mockCargo, result);
    }

    @Test
    public void testFindById_InvalidId() {
        when(cargoRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> cargoDatabaseService.findById(100));
    }

    @Test
    public void testSaveOrUpdateCargo() {
        Cargo cargoToSave = new Cargo();
        // Здесь можно задать необходимые значения для объекта cargoToSave

        when(cargoRepository.save(any(Cargo.class))).thenReturn(cargoToSave);

        Cargo result = cargoDatabaseService.saveOrUpdateCargo(cargoToSave);

        assertNotNull(result);
    }
}
