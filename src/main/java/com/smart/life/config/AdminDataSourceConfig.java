package com.smart.life.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
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
public class AdminDataSourceConfig {

    private Environment env;

    public AdminDataSourceConfig(Environment env) {
        this.env = env;
    }

    @Primary
    @Bean(name = "adminDatasourceBean")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("spring.datasource.driver-class-name")));
        return dataSource;
    }

    @Primary
    @Bean(name = "adminEntityManagerBean")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                       @Qualifier("adminDatasourceBean") DataSource dataSource) {
        return builder.dataSource(dataSource)
                .packages("com.smart.life.")
                .persistenceUnit("adminEntityManager")
                .build();
    }

    @Primary
    @Bean(name = "adminTransactionManagerBean")
    public PlatformTransactionManager transactionManager(
            @Qualifier("adminEntityManagerBean") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
