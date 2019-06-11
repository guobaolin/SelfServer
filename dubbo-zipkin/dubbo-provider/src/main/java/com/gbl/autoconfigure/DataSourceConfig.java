package com.gbl.autoconfigure;

import com.gbl.configure.DatabaseType;
import com.gbl.configure.DynamicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by guobaolin on 2018/11/2.
 */
@Configuration
public class DataSourceConfig {

    @Autowired
    private Environment env;

    @Bean(name = "masterDataSource")
    public DataSource masterDataSource() throws SQLException {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url(env.getProperty("spring.datasource.master.url"));
        dataSourceBuilder.driverClassName(env.getProperty("spring.datasource.master.driverClassName"));
        dataSourceBuilder.username(env.getProperty("spring.datasource.master.username"));
        dataSourceBuilder.password(env.getProperty("spring.datasource.master.password"));
        return dataSourceBuilder.build();
    }

    @Bean(name = "slaveDataSource")
    public DataSource slaveDataSource() throws SQLException {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url(env.getProperty("spring.datasource.slave.url"));
        dataSourceBuilder.driverClassName(env.getProperty("spring.datasource.slave.driverClassName"));
        dataSourceBuilder.username(env.getProperty("spring.datasource.slave.username"));
        dataSourceBuilder.password(env.getProperty("spring.datasource.slave.password"));
        return dataSourceBuilder.build();
    }

    @Bean
    @Primary
    public DynamicDataSource dataSource(@Qualifier("slaveDataSource") DataSource slave,
                                        @Qualifier("masterDataSource") DataSource master) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DatabaseType.master, master);
        targetDataSources.put(DatabaseType.slave, slave);

        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSources);
        dataSource.setDefaultTargetDataSource(master);

        String read = env.getProperty("spring.datasource.read");
        dataSource.setMethodType(DatabaseType.slave, read);

        String write = env.getProperty("spring.datasource.write");
        dataSource.setMethodType(DatabaseType.master, write);

        return dataSource;
    }

}
