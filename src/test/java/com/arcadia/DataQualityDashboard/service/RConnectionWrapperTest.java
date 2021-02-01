package com.arcadia.DataQualityDashboard.service;

import com.arcadia.DataQualityDashboard.dto.DbSettings;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RConnectionWrapperTest {
    private RConnectionWrapper rConnectionWrapper;

    private final DbSettings dbSettings = new DbSettings(
            "sql server",
            "822JNJ16S03V",
            1433,
            "CDM_CPRD",
            "dbo",
            "cdm_builder",
            "builder1!"
    );

    @SneakyThrows
    @BeforeEach
    void setUp() {
        rConnectionWrapper = new RConnectionWrapper();
        rConnectionWrapper.loadScripts();
    }

    @AfterEach
    void tearDown() {
        rConnectionWrapper.close();
    }

    @Test
    void dataQualityCheck() throws RException {
        String result = rConnectionWrapper.checkDataQuality(dbSettings);
        System.out.println(result);
    }

    @Test
    void loadScripts() {
        // Loading in setUp method
    }
}
