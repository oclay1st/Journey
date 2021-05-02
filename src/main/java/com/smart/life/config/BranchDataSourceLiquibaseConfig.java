package com.smart.life.config;

import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
@ConditionalOnProperty(prefix = "journey.liquibase.branch", name = "enable", matchIfMissing = true)
public class BranchDataSourceLiquibaseConfig {

    @Bean(name = "branchLiquibaseProperties")
    @ConfigurationProperties(prefix = "journey.liquibase.branch")
    public LiquibaseProperties branchLiquibaseProperties() {
        return new LiquibaseProperties();
    }

    @Bean
    @DependsOn("branchDataSourceBean")
    public BranchDataSourceLiquibase branchDataSourceLiquibase(Map<Object, Object> targetDataSources,
            @Qualifier("branchLiquibaseProperties") LiquibaseProperties branchLiquibaseProperties) {
        return new BranchDataSourceLiquibase(targetDataSources, branchLiquibaseProperties);
    }
}
