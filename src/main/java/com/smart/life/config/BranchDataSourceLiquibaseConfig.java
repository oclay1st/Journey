package com.smart.life.config;

import java.util.Map;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
@ConditionalOnProperty(prefix = "spring.liquibase", name = "enable", matchIfMissing = true)
@EnableConfigurationProperties(LiquibaseProperties.class)
public class BranchDataSourceLiquibaseConfig {

    private LiquibaseProperties properties;
    
    BranchDataSourceLiquibaseConfig(LiquibaseProperties properties){
        this.properties = properties;
    }

    @Bean
    @DependsOn("branchDataSourceBean")
    public BranchDataSourceLiquibase branchDataSourceLiquibase(Map<Object, Object> targetDataSources) {
        return new BranchDataSourceLiquibase(targetDataSources, properties);
    }
}
