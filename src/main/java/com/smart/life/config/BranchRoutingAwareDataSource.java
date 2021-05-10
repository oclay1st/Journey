package com.smart.life.config;

import java.util.Map;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class BranchRoutingAwareDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return BranchContextHolder.get();
    }

    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        if (BranchContextHolder.get() == null) {
            String firstDataSource = String.valueOf(targetDataSources.keySet().iterator().next());
            BranchContextHolder.set(firstDataSource);
        }
        super.setTargetDataSources(targetDataSources);
    }
}
