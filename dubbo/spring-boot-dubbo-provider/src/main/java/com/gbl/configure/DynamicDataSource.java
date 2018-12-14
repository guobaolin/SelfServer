package com.gbl.configure;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by guobaolin on 2018/11/2.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    protected static final Map<DatabaseType, List<String> /* content */> METHOD_TYPE_MAP = new HashMap<>();

    @Override
    protected Object determineCurrentLookupKey() {
        DatabaseType type = DatabaseContextHolder.getDatabaseType();
        logger.info("==================dataSource =================" + type);
        return type;
    }

    public void setMethodType(DatabaseType type, String content){
        List<String> list = Arrays.asList(content.split(","));
        METHOD_TYPE_MAP.put(type, list);
    }
}
