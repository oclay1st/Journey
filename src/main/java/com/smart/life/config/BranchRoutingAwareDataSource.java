package com.smart.life.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class BranchRoutingAwareDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return BranchContextHolder.get();
    }
}
