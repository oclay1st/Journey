package com.smart.life;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JourneyApplication {

    public static void main(String[] args) {
        SpringApplication.run(JourneyApplication.class, args);
    }

    @Bean(name = "flyway")
    public Flyway flyawayConfig() {
        final FluentConfiguration configuration = new FluentConfiguration();
        return new Flyway(configuration);
    }
}
