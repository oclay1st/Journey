package com.smart.life.config;

import java.util.Map;

public interface DataSourceProvider {

    void refresh();
    
    Map<Object, Object> getDataSources();
 
}
