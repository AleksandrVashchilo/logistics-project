package com.java_43e.logisticsproject.service.database;
import com.java_43e.logisticsproject.repository.ReportRepository;
import com.java_43e.logisticsproject.service.impl.ReportDatabaseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ReportDatabaseServiceTest {

    @Mock
    private ReportRepository reportRepository;

    @InjectMocks
    private ReportDatabaseServiceImpl reportDatabaseService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGenerateReportData() {
        // Создаем список объектов, который ожидаем получить из репозитория
        List<Object[]> reportData = new ArrayList<>();
        reportData.add(new Object[] {"Order1", "Cargo1"});
        reportData.add(new Object[] {"Order2", "Cargo2"});

        // Настройка поведения mock объекта
        when(reportRepository.findOpenOrdersWithCargos()).thenReturn(reportData);

        // Вызов тестируемого метода
        List<Object[]> result = reportDatabaseService.generateReportData();

        // Проверки
        assertEquals(2, result.size());
        assertEquals("Order1", result.get(0)[0]);
        assertEquals("Cargo1", result.get(0)[1]);
        assertEquals("Order2", result.get(1)[0]);
        assertEquals("Cargo2", result.get(1)[1]);

        // Проверяем, что метод findOpenOrdersWithCargos вызывался один раз
        verify(reportRepository, times(1)).findOpenOrdersWithCargos();
    }
}
