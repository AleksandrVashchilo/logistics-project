package com.java_43e.logisticsproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching
@EnableScheduling
@PropertySource(value = "classpath:variables.properties")
public class LogisticsProjectApplication {

    public static void main(String[] args) {

        SpringApplication.run(LogisticsProjectApplication.class, args);
    }

}
