package com.justme8code.utterfresh_production_gathering_sys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class UtterfreshProductionGatheringSysApplication {

    public static void main(String[] args) {
        SpringApplication.run(UtterfreshProductionGatheringSysApplication.class, args);
    }
}
