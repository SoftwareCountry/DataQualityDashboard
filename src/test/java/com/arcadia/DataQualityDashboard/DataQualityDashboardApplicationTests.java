package com.arcadia.DataQualityDashboard;

import org.apache.commons.lang3.SystemUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DataQualityDashboardApplicationTests {

    @Test
    void contextLoads() {
        System.out.printf("Is Unix: %b%n", SystemUtils.IS_OS_UNIX);
        System.out.printf("Is Java 15: %b", SystemUtils.IS_JAVA_15);
    }

}
