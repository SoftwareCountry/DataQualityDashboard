package com.arcadia.DataQualityDashboard.service;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.arcadia.DataQualityDashboard.service.TestProperties.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RConnectionWrapperTest {
    private RConnectionWrapper rConnectionWrapper;

    @SneakyThrows
    @BeforeEach
    void setUp() {
        RConnectionCreator creator = new RConnectionCreator(rServerProperties);
        rConnectionWrapper = creator.createRConnection();
    }

    @AfterEach
    void tearDown() {
        rConnectionWrapper.close();
    }

    @Test
    void loadScripts() {
        // Loading in setUp method
        // Windows OS
    }

    @Test
    void dataQualityCheck() throws RException, DbTypeNotSupportedException {
        // Windows OS
        String result = rConnectionWrapper.checkDataQuality(dbSettings, "");
        assertNotNull(result);
    }
}
