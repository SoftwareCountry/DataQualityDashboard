package com.arcadia.DataQualityDashboard.controller;

import com.arcadia.DataQualityDashboard.dto.DbSettings;
import com.arcadia.DataQualityDashboard.service.RException;
import com.arcadia.DataQualityDashboard.service.CheckDataQualityService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class CheckDataQualityController {

    private final CheckDataQualityService checkDataQualityService;

    @SneakyThrows
    @GetMapping
    public String dataQualityCheck(DbSettings dbSettings, String userId) {
        try {
            return checkDataQualityService.checkDataQuality(dbSettings, userId);
        } catch (RException e) {
            return e.getMessage();
        }
    }
}
