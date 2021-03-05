package com.arcadia.DataQualityDashboard.service;

import org.junit.jupiter.api.Test;

import static com.arcadia.DataQualityDashboard.service.TestProperties.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RConnectionWrapperTest {

    @Test
    void loadScripts() {
        // Windows OS
        RConnectionWrapper connection = new RConnectionCreator(rServerProperties)
                .setLoadScripts(loadScripts)
                .createRConnection();

        connection.close();
    }

    @Test
    void dataQualityCheck() throws RException, DbTypeNotSupportedException {
        // Windows OS
        RConnectionWrapper connection = new RConnectionCreator(rServerProperties)
                .setLoadScripts(loadScripts)
                .createRConnection();

        String result = connection.checkDataQuality(dbSettings, "");
        connection.close();

        assertNotNull(result);
    }
}
