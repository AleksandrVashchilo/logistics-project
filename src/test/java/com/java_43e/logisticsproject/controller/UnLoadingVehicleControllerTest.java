package com.java_43e.logisticsproject.controller;

import static org.mockito.Mockito.*;

import com.java_43e.logisticsproject.service.database.UnLoadingVehicleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
@WebMvcTest(UnLoadingVehicleController.class)
public class UnLoadingVehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UnLoadingVehicleService unLoadingVehicleService;

    @Test
    public void testUnloadVehicle_Success() throws Exception {
        int orderId = 1;

        // Устанавливаем мок-поведение для сервиса
        doNothing().when(unLoadingVehicleService).unloadVehicle(orderId);

        // Выполняем POST-запрос на эндпоинт /api/upload-vehicle/{orderId}
        mockMvc.perform(MockMvcRequestBuilders.post("/api/upload-vehicle/{orderId}", orderId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Vehicle unloaded successfully."));
    }

    @Test
    public void testUnloadVehicle_Failure() throws Exception {
        int orderId = 1;
        String errorMessage = "Failed to unload vehicle";

        // Устанавливаем мок-поведение для сервиса, чтобы выбросить исключение
        doThrow(new Exception(errorMessage)).when(unLoadingVehicleService).unloadVehicle(orderId);

        // Выполняем POST-запрос на эндпоинт /api/upload-vehicle/{orderId}
        mockMvc.perform(MockMvcRequestBuilders.post("/api/upload-vehicle/{orderId}", orderId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andExpect(MockMvcResultMatchers.content().string("Failed to unload vehicle: " + errorMessage));
    }
}
