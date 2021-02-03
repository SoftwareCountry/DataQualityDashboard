package com.arcadia.DataQualityDashboard.service;

import com.arcadia.DataQualityDashboard.properties.RServeProperties;
import org.junit.jupiter.api.Test;

class RConnectionCreatorTest {

    private final RServeProperties properties = new RServeProperties(
            "C:/Program Files/R/R-4.0.3/bin/x64/R.exe",
            "127.0.0.1",
            6311
    );

    @Test
    void createMultipleRConnection() {
        RConnectionCreator creator = new RConnectionCreator(properties);

        RConnectionWrapper connection1 = creator.createRConnection();
        RConnectionWrapper connection2 = creator.createRConnection();

        connection1.close();
        connection2.close();
    }
}
