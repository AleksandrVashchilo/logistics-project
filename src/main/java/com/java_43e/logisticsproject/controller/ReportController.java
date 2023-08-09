package com.java_43e.logisticsproject.controller;

import com.java_43e.logisticsproject.service.database.ReportDatabaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportDatabaseService reportDatabaseService;

    @GetMapping
    public List<Object[]> getReports() {
        return reportDatabaseService.generateReportData();
    }
}