package com.smart.life.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(transactionManagerRef = "branchTransactionManagerBean", entityManagerFactoryRef = "branchEntityManagerBean", basePackages = "com.smart.life.saas")
public class BranchDataSourceConfig {

  
    @Bean(name = "branchDataSourceBean")
    public DataSource dataSource(DataSourceProvider branchDataSourceProvider) {
        AbstractRoutingDataSource dataSource = new BranchRoutingAwareDataSource();
        dataSource.setTargetDataSources(branchDataSourceProvider.getDataSources());
        dataSource.afterPropertiesSet();
        return dataSource;
    }

    @Bean(name = "branchEntityManagerBean")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Qualifier("branchDataSourceBean") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean localEntityManager = new LocalContainerEntityManagerFactoryBean();
        localEntityManager.setPersistenceUnitName("branchManager");
        localEntityManager.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        localEntityManager.setJpaDialect(new HibernateJpaDialect());
        localEntityManager.setDataSource(dataSource);
        localEntityManager.setPackagesToScan("com.smart.life.saas");
        localEntityManager.afterPropertiesSet();
        return localEntityManager;
    }

    @Bean(name = "branchTransactionManagerBean")
    @DependsOn("branchEntityManagerBean")
    public PlatformTransactionManager branchTransactionManager(
            @Qualifier("branchEntityManagerBean") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
