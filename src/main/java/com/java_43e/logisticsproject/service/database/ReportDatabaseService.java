package com.java_43e.logisticsproject.service.database;

import java.util.List;

public interface ReportDatabaseService {
    /**
     * generating a report from the orders table and the cargos table according to the condition where the order is not closed
     *
     * @return
     */
    List<Object[]> generateReportData();
}
