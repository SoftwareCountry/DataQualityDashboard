package com.arcadia.DataQualityDashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class DataQualityDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataQualityDashboardApplication.class, args);
	}

}
