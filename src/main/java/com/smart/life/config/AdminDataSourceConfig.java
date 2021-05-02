package com.smart.life.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "adminEntityManagerBean", transactionManagerRef = "adminTransactionManagerBean", basePackages = "com.smart.life.admin.infrastructure")
@EnableConfigurationProperties(DataSourceProperties.class)
public class AdminDataSourceConfig {

    private DataSourceProperties dataSourceProperties;

    public AdminDataSourceConfig(DataSourceProperties dataSourceProperties) {
        this.dataSourceProperties = dataSourceProperties;
    }

    @Primary
    @Bean(name = "adminDatasourceBean")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(dataSourceProperties.getUrl());
        dataSource.setUsername(dataSourceProperties.getUsername());
        dataSource.setPassword(dataSourceProperties.getPassword());
        dataSource.setDriverClassName(Objects.requireNonNull(dataSourceProperties.getDriverClassName()));
        return dataSource;
    }

    @Primary
    @Bean(name = "adminEntityManagerBean")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
            @Qualifier("adminDatasourceBean") DataSource dataSource) {
        return builder.dataSource(dataSource).packages("com.smart.life.").persistenceUnit("adminEntityManager").build();
    }

    @Primary
    @Bean(name = "adminTransactionManagerBean")
    public PlatformTransactionManager transactionManager(
            @Qualifier("adminEntityManagerBean") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Primary
    @Bean(name = "liquibaseProperties")
    @ConfigurationProperties(prefix = "spring.liquibase")
    public LiquibaseProperties liquibaseProperties() {
        return new LiquibaseProperties();
    }
}
