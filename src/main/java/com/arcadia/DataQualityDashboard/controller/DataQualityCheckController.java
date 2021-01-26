package com.arcadia.DataQualityDashboard.controller;

import com.arcadia.DataQualityDashboard.dto.DbSettings;
import com.arcadia.DataQualityDashboard.service.RException;
import com.arcadia.DataQualityDashboard.service.RServerService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class DataQualityCheckController {

    private final RServerService rServerService;

    @SneakyThrows
    @GetMapping
    public String dataQualityCheck(DbSettings dbSettings) {
        try {
            return rServerService.dataQualityCheck(dbSettings);
        } catch (RException e) {
            return e.getMessage();
        }
    }
}
