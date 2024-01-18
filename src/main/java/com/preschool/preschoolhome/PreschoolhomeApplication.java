package com.preschool.preschoolhome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class PreschoolhomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(PreschoolhomeApplication.class, args);
    }

}
