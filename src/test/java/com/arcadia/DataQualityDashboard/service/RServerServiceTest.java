package com.arcadia.DataQualityDashboard.service;

import com.arcadia.DataQualityDashboard.dto.DbSettings;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.Rserve.RserveException;

class RServerServiceTest {

    private final RServerService rServerService = new RServerService();

    private final DbSettings dbSettings = new DbSettings(
            "sql server",
            "822JNJ16S03V",
            1433,
            "CDM_CPRD",
            "dbo",
            "cdm_builder",
            "builder1!"
    );

    RServerServiceTest() throws RserveException {
    }

    @BeforeEach
    void setUp() {
        rServerService.init();
    }

    @AfterEach
    void tearDown() {
        rServerService.destroy();
    }

    @Test
    void dataQualityCheck() throws REngineException, REXPMismatchException, RException {
        String result = rServerService.dataQualityCheck(dbSettings);
        System.out.println(result);
    }
}
