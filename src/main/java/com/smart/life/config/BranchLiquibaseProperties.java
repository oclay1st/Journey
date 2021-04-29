package com.smart.life.config;

import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "journey.liquibase.branch", ignoreUnknownFields = false)
public class BranchLiquibaseProperties extends LiquibaseProperties{

    public BranchLiquibaseProperties(){
        super();
    }
    
}
