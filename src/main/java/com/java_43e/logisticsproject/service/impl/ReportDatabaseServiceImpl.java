package com.java_43e.logisticsproject.service.impl;
import com.java_43e.logisticsproject.repository.ReportRepository;
import com.java_43e.logisticsproject.service.database.ReportDatabaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportDatabaseServiceImpl implements ReportDatabaseService {

    private final ReportRepository reportRepository;

    @Override
    public List<Object[]> generateReportData() {
        return reportRepository.findOpenOrdersWithCargos();
    }
}

    //Проверка, печать в консоль
    //@Override
    //public List<Object[]> generateReportData() {
      //  List<Object[]> reportData = reportRepository.findOpenOrdersWithCargos();

        // Распечатываем содержимое reportData в консоль
     //   for (Object[] row : reportData) {
       //     for (Object obj : row) {
        //        System.out.print(obj + " ");
        //    }
      //      System.out.println(); // Перевод строки после каждой строки данных
      //  }

      //  return reportData; // Возвращаем полученные данные, если они нужны для дальнейшей обработки
  //  }
