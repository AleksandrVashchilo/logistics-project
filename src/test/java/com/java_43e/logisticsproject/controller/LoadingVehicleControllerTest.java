package com.java_43e.logisticsproject.controller;
import static org.mockito.Mockito.*;

import com.java_43e.logisticsproject.service.database.LoadingVehicleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
@WebMvcTest(LoadingVehicleController.class)
public class LoadingVehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoadingVehicleService loadingVehicleService;

    @Test
    public void testLoadVehicle_Success() throws Exception {
        int orderId = 1;

        // Выполняем POST-запрос на эндпоинт /api/load-vehicle/{orderId}
        mockMvc.perform(MockMvcRequestBuilders.post("/api/load-vehicle/{orderId}", orderId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Vehicle loaded successfully."));

        // Проверяем, что метод loadingVehicleService.loadVehicle был вызван с правильным orderId
        verify(loadingVehicleService, times(1)).loadVehicle(orderId);
    }

    @Test
    public void testLoadVehicle_Failure() throws Exception {
        int orderId = 1;
        String errorMessage = "Failed to load vehicle: Order not found.";

        // Устанавливаем мок-поведение для loadingVehicleService.loadVehicle, чтобы выбросить исключение
        doThrow(new RuntimeException("Order not found.")).when(loadingVehicleService).loadVehicle(orderId);

        // Выполняем POST-запрос на эндпоинт /api/load-vehicle/{orderId}
        mockMvc.perform(MockMvcRequestBuilders.post("/api/load-vehicle/{orderId}", orderId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andExpect(MockMvcResultMatchers.content().string(errorMessage));

        // Проверяем, что метод loadingVehicleService.loadVehicle был вызван с правильным orderId
        verify(loadingVehicleService, times(1)).loadVehicle(orderId);
    }
}
