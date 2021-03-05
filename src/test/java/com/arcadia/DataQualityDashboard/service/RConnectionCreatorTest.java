package com.arcadia.DataQualityDashboard.service;

import org.junit.jupiter.api.Test;

import static com.arcadia.DataQualityDashboard.service.TestProperties.rServerProperties;

class RConnectionCreatorTest {

    @Test
    void createMultipleRConnection() {
        RConnectionCreator creator = new RConnectionCreator(rServerProperties);

        RConnectionWrapper connection1 = creator.createRConnection();
        RConnectionWrapper connection2 = creator.createRConnection();

        connection1.close();
        connection2.close();
    }
}
