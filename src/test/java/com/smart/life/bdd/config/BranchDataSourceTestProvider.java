package com.smart.life.bdd.config;

import java.util.HashMap;
import java.util.Map;

import com.smart.life.config.DataSourceProvider;

import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

@Primary
@Component
public class BranchDataSourceTestProvider implements DataSourceProvider{
     
    private Map<Object, Object> dataSources;


    public BranchDataSourceTestProvider() {
        this.generateDataSources();
    }

    private void generateDataSources() {
        Map<Object, Object> targetDataSources = new HashMap<>();
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:h2:mem:branchdb");
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUsername("test");
        dataSource.setPassword("test");
        targetDataSources.put("1", dataSource);
        dataSources = targetDataSources;
    }

    public void refresh(){
        generateDataSources();
    }

    public Map<Object, Object> getDataSources(){
        return dataSources;
    }
}
