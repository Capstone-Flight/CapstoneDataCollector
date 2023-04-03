package com.capstone.collector.capstonedatacollector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CapstoneDataCollectorApplication {

    public static void main(String[] args) {
        SpringApplication.run(CapstoneDataCollectorApplication.class, args);
    }

}
