package com.smart.life.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.smart.life.admin.domain.org.Org;
import com.smart.life.admin.domain.org.OrgRepository;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.springframework.stereotype.Component;

@Component
public class BranchDataSourceProvider implements DataSourceProvider{
    
    private Map<Object, Object> dataSources;

    private final OrgRepository orgRepository;

    public BranchDataSourceProvider(OrgRepository orgRepository) {
        this.orgRepository = orgRepository;
        this.generateDataSources();
    }

    private void generateDataSources() {
        Map<Object, Object> targetDataSources = new HashMap<>();
        for (Org org : orgRepository.findAllByActiveTrue()) {        
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
        dataSources = targetDataSources;
    }

    public void refresh(){
        generateDataSources();
    }

    public Map<Object, Object> getDataSources(){
        return dataSources;
    }
 
}
