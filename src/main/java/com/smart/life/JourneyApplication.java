package com.smart.life;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class JourneyApplication {

    public static void main(String[] args) {
        SpringApplication.run(JourneyApplication.class, args);
    }
}
