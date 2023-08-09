package com.java_43e.logisticsproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java_43e.logisticsproject.entity.Bid;
import com.java_43e.logisticsproject.service.database.BidDatabaseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.mockito.Mockito.when;

@WebMvcTest(BidController.class)
public class BidControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BidDatabaseService bidDatabaseService;

    @Test
    public void testAddBid() throws Exception {
        // Подготовка данных для теста
        Bid bid = new Bid(/* Здесь инициализируйте объект Bid с нужными данными */);

        // Выполняем POST-запрос на эндпоинт /bid-add/
        mockMvc.perform(MockMvcRequestBuilders.post("/bid-add/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(bid)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists()); // Проверяем, что в ответе есть поле 'id'
    }

    @Test
    public void testGetBid() throws Exception {
        int bidId = 1;
        Bid bid = new Bid(/* Здесь инициализируйте объект Bid с нужными данными */);
        when(bidDatabaseService.findById(bidId)).thenReturn(Optional.of(bid));

        // Выполняем GET-запрос на эндпоинт /bid-get/{id}
        mockMvc.perform(MockMvcRequestBuilders.get("/bid-get/{id}", bidId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(bidId)); // Проверяем, что в ответе поле 'id' имеет ожидаемое значение
    }

    @Test
    public void testCheckBid1Km() throws Exception {
        int bidId = 1;
        boolean result = true; // Задайте ожидаемый результат
        when(bidDatabaseService.checkBid1Km(bidId)).thenReturn(result);

        // Выполняем GET-запрос на эндпоинт /bid-check-1km/{id}
        mockMvc.perform(MockMvcRequestBuilders.get("/bid-check-1km/{id}", bidId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(result ? "Все хорошо" : "Все плохо")); // Проверяем, что в ответе верный текст
    }

    @Test
    public void testCheckBidDot() throws Exception {
        int bidId = 1;
        boolean result = true; // Задайте ожидаемый результат
        when(bidDatabaseService.checkBidDot(bidId)).thenReturn(result);

        // Выполняем GET-запрос на эндпоинт /bid-check-dot/{id}
        mockMvc.perform(MockMvcRequestBuilders.get("/bid-check-dot/{id}", bidId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(result ? "Все хорошо" : "Все плохо"));
    }

    @Test
    public void testCheckBidTemperature() throws Exception {
        int bidId = 1;
        boolean result = true; // Задайте ожидаемый результат
        when(bidDatabaseService.checkBidTemperature(bidId)).thenReturn(result);

        // Выполняем GET-запрос на эндпоинт /bid-check-temperature/{id}
        mockMvc.perform(MockMvcRequestBuilders.get("/bid-check-temperature/{id}", bidId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(result ? "Все хорошо" : "Все плохо"));
    }

    @Test
    public void testCheckBidWeight() throws Exception {
        int bidId = 1;
        boolean result = true; // Задайте ожидаемый результат
        when(bidDatabaseService.checkBidWeight(bidId)).thenReturn(result);

        // Выполняем GET-запрос на эндпоинт /bid-check-weight/{id}
        mockMvc.perform(MockMvcRequestBuilders.get("/bid-check-weight/{id}", bidId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(result ? "Все хорошо" : "Все плохо"));
    }

    @Test
    public void testCheckBidVolume() throws Exception {
        int bidId = 1;
        boolean result = true; // Задайте ожидаемый результат
        when(bidDatabaseService.checkBidVolume(bidId)).thenReturn(result);

        // Выполняем GET-запрос на эндпоинт /bid-check-volume/{id}
        mockMvc.perform(MockMvcRequestBuilders.get("/bid-check-volume/{id}", bidId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(result ? "Все хорошо" : "Все плохо"));
    }

    @Test
    public void testCheckBidPlacePallet() throws Exception {
        int bidId = 1;
        boolean result = true; // Задайте ожидаемый результат
        when(bidDatabaseService.checkBidPlacePallet(bidId)).thenReturn(result);

        // Выполняем GET-запрос на эндпоинт /bid-check-place-pallet/{id}
        mockMvc.perform(MockMvcRequestBuilders.get("/bid-check-place-pallet/{id}", bidId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(result ? "Все хорошо" : "Все плохо"));
    }

}
