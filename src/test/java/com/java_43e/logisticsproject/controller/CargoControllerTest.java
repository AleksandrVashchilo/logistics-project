package com.java_43e.logisticsproject.controller;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java_43e.logisticsproject.entity.Cargo;
import com.java_43e.logisticsproject.service.database.CargoDatabaseService;
import com.java_43e.logisticsproject.service.impl.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(CargoController.class)
public class CargoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CargoDatabaseService cargoDatabaseService;

    private List<Cargo> mockCargoList;

    @BeforeEach
    public void setup() {
        // Подготовка мок-данных для каждого теста
        mockCargoList = new ArrayList<>();
        Cargo cargo1 = new Cargo();
        cargo1.setCargoId(1);
        cargo1.setCargoName("Товар 1");
        Cargo cargo2 = new Cargo();
        cargo2.setCargoId(2);
        cargo2.setCargoName("Товар 2");
    }

    @Test
    public void testGetAllCargos() throws Exception {
        // Устанавливаем мок-данные для сервиса
        when(cargoDatabaseService.getCargo()).thenReturn(mockCargoList);

        // Выполняем GET-запрос на эндпоинт /api/cargo
        mockMvc.perform(MockMvcRequestBuilders.get("/api/cargo"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].cargoName").value("Товар 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].cargoName").value("Товар 2"));
    }

    @Test
    public void testGetCargoById_ValidId_Success() throws Exception {
        int cargoId = 1;
        Cargo mockCargo = new Cargo();
        mockCargo.setCargoId(cargoId);
        mockCargo.setCargoName("Товар 1");
        // Здесь вы можете установить значения для других полей класса Cargo для объекта mockCargo
        // Пример:
        // mockCargo.setDotClass(3);
        // mockCargo.setWeight(500);
        // mockCargo.setVolume(1000);
        // ...

        when(cargoDatabaseService.findById(cargoId)).thenReturn(mockCargo);

        // Выполняем GET-запрос на эндпоинт /api/cargo/{id}
        mockMvc.perform(MockMvcRequestBuilders.get("/api/cargo/{id}", cargoId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(cargoId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cargoName").value("Товар 1"));
    }

    @Test
    public void testGetCargoById_ExistingId_Success() throws Exception {
        int cargoId = 1;
        Cargo mockCargo = new Cargo();
        mockCargo.setCargoId(cargoId);
        mockCargo.setCargoName("Товар 1");
        // Здесь вы можете установить значения для других полей класса Cargo для объекта mockCargo
        // Пример:
        // mockCargo.setDotClass(3);
        // mockCargo.setWeight(500);
        // mockCargo.setVolume(1000);
        // ...

        when(cargoDatabaseService.findById(cargoId)).thenReturn(mockCargo);

        // Выполняем GET-запрос на эндпоинт /api/cargo/{id}
        mockMvc.perform(MockMvcRequestBuilders.get("/api/cargo/{id}", cargoId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(cargoId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cargoName").value("Товар 1"));
    }

    @Test
    public void testGetCargoById_ValidId_Successful() throws Exception {
        int cargoId = 1;
        Cargo mockCargo = new Cargo();
        mockCargo.setCargoId(cargoId);
        mockCargo.setCargoName("Товар 1");
        // Здесь вы можете установить значения для других полей класса Cargo для объекта mockCargo
        // Пример:
        // mockCargo.setDotClass(3);
        // mockCargo.setWeight(500);
        // mockCargo.setVolume(1000);
        // ...

        when(cargoDatabaseService.findById(cargoId)).thenReturn(mockCargo);

        // Выполняем GET-запрос на эндпоинт /api/cargo/{id}
        mockMvc.perform(MockMvcRequestBuilders.get("/api/cargo/{id}", cargoId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(cargoId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cargoName").value("Товар 1"));
    }

    @Test
    public void testGetCargoById_ExistingId_Successful() throws Exception {
        int cargoId = 1;
        Cargo mockCargo = new Cargo();
        mockCargo.setCargoId(cargoId);
        mockCargo.setCargoName("Товар 1");
        // Здесь вы можете установить значения для других полей класса Cargo для объекта mockCargo
        // Пример:
        // mockCargo.setDotClass(3);
        // mockCargo.setWeight(500);
        // mockCargo.setVolume(1000);
        // ...

        when(cargoDatabaseService.findById(cargoId)).thenReturn(mockCargo);

        // Выполняем GET-запрос на эндпоинт /api/cargo/{id}
        mockMvc.perform(MockMvcRequestBuilders.get("/api/cargo/{id}", cargoId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(cargoId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cargoName").value("Товар 1"));
    }

    @Test
    public void testGetCargoById_InvalidId_NotFound() throws Exception {
        int nonExistentCargoId = 100; // Несуществующий ID
        when(cargoDatabaseService.findById(anyInt())).thenThrow(new ResourceNotFoundException());

        // Выполняем GET-запрос на эндпоинт /api/cargo/{id} с несуществующим ID
        mockMvc.perform(MockMvcRequestBuilders.get("/api/cargo/{id}", nonExistentCargoId))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testAddCargo() throws Exception {
        Cargo newCargo = new Cargo(/* Здесь инициализируйте объект нового груза */);

        // Выполняем POST-запрос на эндпоинт /api/cargo
        mockMvc.perform(MockMvcRequestBuilders.post("/api/cargo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(newCargo)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.cargoName").value(newCargo.getCargoName()));
    }

    @Test
    public void testUpdateCargo_ValidId() throws Exception {
        int cargoId = 1;
        Cargo updatedCargo = new Cargo();
        updatedCargo.setCargoId(cargoId);
        updatedCargo.setCargoName("Обновленный товар 1");
        // Здесь вы можете установить значения для других полей класса Cargo, если они требуют обновления.
        // Пример:
        // updatedCargo.setDotClass(3);
        // updatedCargo.setWeight(500);
        // updatedCargo.setVolume(1000);
        // ...

        when(cargoDatabaseService.findById(cargoId)).thenReturn(updatedCargo);

        // Выполняем PUT-запрос на эндпоинт /api/cargo/{id}
        mockMvc.perform(MockMvcRequestBuilders.put("/api/cargo/{id}", cargoId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updatedCargo)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(cargoId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cargoName").value(updatedCargo.getCargoName()));
    }

    @Test
    public void testUpdateCargo_InvalidId() throws Exception {
        int cargoId = 100; // Не существующий ID
        Cargo updatedCargo = new Cargo();
        updatedCargo.setCargoId(cargoId);
        updatedCargo.setCargoName("Обновленный товар");
        // Здесь вы можете установить значения для других полей класса Cargo, если они требуют обновления.
        // Пример:
        // updatedCargo.setDotClass(3);
        // updatedCargo.setWeight(500);
        // updatedCargo.setVolume(1000);
        // ...

        when(cargoDatabaseService.findById(cargoId)).thenThrow(new ResourceNotFoundException());

        // Выполняем PUT-запрос на эндпоинт /api/cargo/{id}
        mockMvc.perform(MockMvcRequestBuilders.put("/api/cargo/{id}", cargoId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updatedCargo)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
