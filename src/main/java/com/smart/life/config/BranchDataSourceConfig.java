package com.smart.life.config;

import com.smart.life.admin.domain.org.Org;
import com.smart.life.admin.domain.org.OrgRepository;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(transactionManagerRef = "branchTransactionManagerBean", entityManagerFactoryRef = "branchEntityManagerBean", basePackages = "com.smart.life.saas")
public class BranchDataSourceConfig {

    private final OrgRepository orgRepository;

    public BranchDataSourceConfig(OrgRepository orgRepository) {
        this.orgRepository = orgRepository;
    }

    @Bean(name = "targetDataSources")
    public Map<Object, Object> targetDataSources() {
        Map<Object, Object> targetDataSources = new HashMap<>();
        for (Org org : orgRepository.findAllByActiveTrue()) {
            if (BranchContextHolder.get() == null) {
                BranchContextHolder.set(org.getId().toString());
            }
            Properties properties = new Properties();
            properties.setProperty("dataSourceClassName", "org.postgresql.ds.PGSimpleDataSource");
            properties.setProperty("dataSource.user", org.getDbUsername());
            properties.setProperty("dataSource.password", org.getDbPassword());
            properties.setProperty("dataSource.databaseName", org.getDbName());
            properties.setProperty("dataSource.portNumber", org.getDbPort().toString());
            properties.setProperty("dataSource.serverName", org.getDbServer());
            HikariConfig hikariConfig = new HikariConfig(properties);
            HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
            targetDataSources.put(org.getId().toString(), hikariDataSource);
        }
        return targetDataSources;
    }

    @Bean(name = "branchDataSourceBean")
    public DataSource dataSource(Map<Object, Object> targetDataSources) {
        AbstractRoutingDataSource dataSource = new BranchRoutingAwareDataSource();
        dataSource.setTargetDataSources(targetDataSources);
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
