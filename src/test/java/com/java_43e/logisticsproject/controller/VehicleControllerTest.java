package com.java_43e.logisticsproject.controller;
import static org.mockito.Mockito.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java_43e.logisticsproject.entity.Vehicle;
import com.java_43e.logisticsproject.service.database.VehicleDatabaseService;
import com.java_43e.logisticsproject.service.impl.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
@WebMvcTest(VehicleController.class)
public class VehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VehicleDatabaseService vehicleDatabaseService;

    @Test
    public void testGetAllVehicles() throws Exception {
        // Подготовка мок-данных для сервиса
        List<Vehicle> mockVehicleList = new ArrayList<>();
        Vehicle vehicle1 = new Vehicle();
        vehicle1.setVehicleId(1);
        vehicle1.setDriverName("Driver 1");
        Vehicle vehicle2 = new Vehicle();
        vehicle2.setVehicleId(2);
        vehicle2.setDriverName("Driver 2");
        mockVehicleList.add(vehicle1);
        mockVehicleList.add(vehicle2);

        // Устанавливаем мок-поведение для сервиса
        when(vehicleDatabaseService.getVehicle()).thenReturn(mockVehicleList);

        // Выполняем GET-запрос на эндпоинт /api/vehicles/get-all
        mockMvc.perform(MockMvcRequestBuilders.get("/api/vehicles/get-all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].vehicleId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].vehicleId").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].driverName").value("Driver 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].driverName").value("Driver 2"));
    }

    @Test
    public void testGetVehicleById_ValidId_Success() throws Exception {
        int vehicleId = 1;
        Vehicle mockVehicle = new Vehicle();
        mockVehicle.setVehicleId(vehicleId);
        mockVehicle.setDriverName("Driver 1");
        // Здесь вы можете установить значения для других полей класса Vehicle для объекта mockVehicle
        // Пример:
        // mockVehicle.setAutoNumber("ABC123");

        // Устанавливаем мок-поведение для сервиса
        when(vehicleDatabaseService.findById(vehicleId)).thenReturn(mockVehicle);

        // Выполняем GET-запрос на эндпоинт /api/vehicles/{id}
        mockMvc.perform(MockMvcRequestBuilders.get("/api/vehicles/{id}", vehicleId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.vehicleId").value(vehicleId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.driverName").value("Driver 1"));
    }

    @Test
    public void testGetVehicleById_InvalidId_NotFound() throws Exception {
        int nonExistentVehicleId = 100; // Несуществующий ID
        // Устанавливаем мок-поведение для сервиса, чтобы выбросить исключение ResourceNotFoundException
        when(vehicleDatabaseService.findById(nonExistentVehicleId)).thenThrow(new ResourceNotFoundException());

        // Выполняем GET-запрос на эндпоинт /api/vehicles/{id} с несуществующим ID
        mockMvc.perform(MockMvcRequestBuilders.get("/api/vehicles/{id}", nonExistentVehicleId))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testAddVehicle() throws Exception {
        Vehicle newVehicle = new Vehicle();
        // Здесь инициализируйте объект нового транспортного средства newVehicle с необходимыми данными

        // Устанавливаем мок-поведение для сервиса
        doNothing().when(vehicleDatabaseService).saveOrUpdateVehicle(newVehicle);

        // Выполняем POST-запрос на эндпоинт /api/vehicles/add
        mockMvc.perform(MockMvcRequestBuilders.post("/api/vehicles/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(newVehicle)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.driverName").value(newVehicle.getDriverName()));
    }

    @Test
    public void testUpdateVehicle_ValidId() throws Exception {
        int vehicleId = 1;
        Vehicle updatedVehicle = new Vehicle();
        updatedVehicle.setVehicleId(vehicleId);
        updatedVehicle.setDriverName("Updated Driver 1");
        // Здесь вы можете установить значения для других полей класса Vehicle, если они требуют обновления.
        // Пример:
        // updatedVehicle.setAutoNumber("XYZ789");

        // Устанавливаем мок-поведение для сервиса
        when(vehicleDatabaseService.findById(vehicleId)).thenReturn(updatedVehicle);
        doNothing().when(vehicleDatabaseService).saveOrUpdateVehicle(updatedVehicle);

        // Выполняем PUT-запрос на эндпоинт /api/vehicles/{id}
        mockMvc.perform(MockMvcRequestBuilders.put("/api/vehicles/{id}", vehicleId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updatedVehicle)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.vehicleId").value(vehicleId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.driverName").value("Updated Driver 1"));
    }

    @Test
    public void testUpdateVehicle_InvalidId_NotFound() throws Exception {
        int vehicleId = 100; // Не существующий ID
        Vehicle updatedVehicle = new Vehicle();
        updatedVehicle.setVehicleId(vehicleId);
        updatedVehicle.setDriverName("Updated Driver");
        // Здесь вы можете установить значения для других полей класса Vehicle, если они требуют обновления.
        // Пример:
        // updatedVehicle.setAutoNumber("XYZ789");

        // Устанавливаем мок-поведение для сервиса, чтобы выбросить исключение ResourceNotFoundException
        when(vehicleDatabaseService.findById(vehicleId)).thenThrow(new ResourceNotFoundException());

        // Выполняем PUT-запрос на эндпоинт /api/vehicles/{id}
        mockMvc.perform(MockMvcRequestBuilders.put("/api/vehicles/{id}", vehicleId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updatedVehicle)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
