package com.gbl.properties;

import com.baidu.disconf.client.common.annotations.DisconfFile;
import com.baidu.disconf.client.common.annotations.DisconfFileItem;
import org.springframework.boot.context.properties.ConfigurationProperties;

//@ConfigurationProperties
//@ConfigurationProperties(prefix = RedisProperties.REDIS_PREFIX)
//@DisconfFile(filename = "redis.properties", targetDirPath = "config")
public class RedisProperties {
//    public final static String REDIS_PREFIX = "redis";

    private String hostName;

    private Integer port;

    private String password;

    private Integer database;

    private Integer timeout;

    private Integer maxIdle;

    private Integer maxTotal;

    private Integer minEvictableIdleTimeMillis;

    private Integer numTestsPerEvictionRun;

    private long timeBetweenEvictionRunsMillis;

    private boolean testOnBorrow;

    private boolean testWhileIdle;

    @DisconfFileItem(name = "redis.hostName", associateField = "hostName")
    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    @DisconfFileItem(name = "redis.port", associateField = "port")
    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    @DisconfFileItem(name = "redis.password", associateField = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @DisconfFileItem(name = "redis.database", associateField = "database")
    public Integer getDatabase() {
        return database;
    }

    public void setDatabase(Integer database) {
        this.database = database;
    }

    @DisconfFileItem(name = "redis.timeout", associateField = "timeout")
    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    @DisconfFileItem(name = "redis.maxIdle", associateField = "maxIdle")
    public Integer getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(Integer maxIdle) {
        this.maxIdle = maxIdle;
    }

    @DisconfFileItem(name = "redis.maxTotal", associateField = "maxTotal")
    public Integer getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(Integer maxTotal) {
        this.maxTotal = maxTotal;
    }

    @DisconfFileItem(name = "redis.minEvictableIdleTimeMillis", associateField = "minEvictableIdleTimeMillis")
    public Integer getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    public void setMinEvictableIdleTimeMillis(Integer minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }

    @DisconfFileItem(name = "redis.numTestsPerEvictionRun", associateField = "numTestsPerEvictionRun")
    public Integer getNumTestsPerEvictionRun() {
        return numTestsPerEvictionRun;
    }

    public void setNumTestsPerEvictionRun(Integer numTestsPerEvictionRun) {
        this.numTestsPerEvictionRun = numTestsPerEvictionRun;
    }

    @DisconfFileItem(name = "redis.timeBetweenEvictionRunsMillis", associateField = "timeBetweenEvictionRunsMillis")
    public long getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    public void setTimeBetweenEvictionRunsMillis(long timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    @DisconfFileItem(name = "redis.testOnBorrow", associateField = "testOnBorrow")
    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    @DisconfFileItem(name = "redis.testWhileIdle", associateField = "testWhileIdle")
    public boolean isTestWhileIdle() {
        return testWhileIdle;
    }

    public void setTestWhileIdle(boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }

}

