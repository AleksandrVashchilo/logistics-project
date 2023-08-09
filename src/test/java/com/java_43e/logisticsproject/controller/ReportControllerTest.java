package com.java_43e.logisticsproject.controller;
import static org.mockito.Mockito.*;

import com.java_43e.logisticsproject.service.database.ReportDatabaseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
@WebMvcTest(ReportController.class)
public class ReportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReportDatabaseService reportDatabaseService;

    @Test
    public void testGetReports() throws Exception {
        // Устанавливаем мок-данные для сервиса
        List<Object[]> mockReportData = new ArrayList<>();
        Object[] reportData1 = new Object[]{"Report 1", 10, 100.0};
        Object[] reportData2 = new Object[]{"Report 2", 20, 200.0};
        mockReportData.add(reportData1);
        mockReportData.add(reportData2);

        when(reportDatabaseService.generateReportData()).thenReturn(mockReportData);

        // Выполняем GET-запрос на эндпоинт /api/report
        mockMvc.perform(MockMvcRequestBuilders.get("/api/report"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0][0]").value("Report 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0][1]").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0][2]").value(100.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1][0]").value("Report 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1][1]").value(20))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1][2]").value(200.0));
    }
}
