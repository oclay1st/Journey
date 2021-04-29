package com.smart.life.config;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.StopWatch;

import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class BranchDataSourceLiquibase implements InitializingBean, ResourceLoaderAware {

    private Map<Object, Object> datasources;

    private ResourceLoader resourceLoader;

    private LiquibaseProperties properties;

    BranchDataSourceLiquibase(Map<Object, Object> datasources, LiquibaseProperties properties) {
        this.datasources = datasources;
        this.properties = properties;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("Initializing Liquibase migrations:");
        migrateAllDataSources();
    }

    private void migrateAllDataSources() {
        datasources.forEach((branch, datasource) -> migrateBranchDataSource((String) branch, (DataSource) datasource));
    }

    @Async
    private void migrateBranchDataSource(String branchKey, DataSource dataSource) {
        log.info("Initializing migration for Brach: " + branchKey);
        SpringLiquibase liquibase = getSpringLiquibase(dataSource);
        StopWatch watch = new StopWatch();
        try {
            watch.start();
            liquibase.afterPropertiesSet();
            watch.stop();
            log.info("Migrations runned successfully for Branch: {} in {} ms", branchKey, watch.getTotalTimeMillis());
        } catch (LiquibaseException ex) {
            log.error("Error running migrations on Branch: {} caused by {}", branchKey, ex.getMessage());
        }
    }

    private SpringLiquibase getSpringLiquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog(properties.getChangeLog());
        liquibase.setChangeLogParameters(properties.getParameters());
        liquibase.setContexts(properties.getContexts());
        liquibase.setLabels(properties.getLabels());
        liquibase.setDropFirst(properties.isDropFirst());
        liquibase.setShouldRun(properties.isEnabled());
        liquibase.setRollbackFile(properties.getRollbackFile());
        liquibase.setResourceLoader(resourceLoader);
        liquibase.setDataSource(dataSource);
        liquibase.setDefaultSchema(properties.getDefaultSchema());
        liquibase.setLiquibaseSchema(properties.getLiquibaseSchema());
        liquibase.setLiquibaseTablespace(properties.getLiquibaseTablespace());
        liquibase.setDatabaseChangeLogTable(properties.getDatabaseChangeLogTable());
        liquibase.setDatabaseChangeLogLockTable(properties.getDatabaseChangeLogLockTable());
        return liquibase;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

}