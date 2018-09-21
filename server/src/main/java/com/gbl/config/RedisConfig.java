package com.gbl.config;

import com.gbl.properties.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableCaching
@EnableConfigurationProperties(RedisProperties.class)
@PropertySource("classpath:config/redis.properties")
public class RedisConfig extends CachingConfigurerSupport {
    private RedisProperties properties;

    public RedisConfig(RedisProperties properties) {
        this.properties = properties;
    }

    @Bean
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(properties.getMaxIdle());
        jedisPoolConfig.setMaxTotal(properties.getMaxTotal());
        jedisPoolConfig.setMinEvictableIdleTimeMillis(properties.getMinEvictableIdleTimeMillis());
        jedisPoolConfig.setNumTestsPerEvictionRun(properties.getNumTestsPerEvictionRun());
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(properties.getTimeBetweenEvictionRunsMillis());
        jedisPoolConfig.setTestOnBorrow(properties.isTestOnBorrow());
        jedisPoolConfig.setTestWhileIdle(properties.isTestWhileIdle());
        return jedisPoolConfig;
    }

    @Bean
    public JedisPool redisPoolFactory(JedisPoolConfig jedisPoolConfig) {
        JedisPool jedisPool = new JedisPool(
                jedisPoolConfig,
                properties.getHostName(),
                properties.getPort(),
                properties.getTimeout()
        );
        return jedisPool;
    }

//    @Bean
//    public RedisStandaloneConfiguration redisStandaloneConfiguration(){
//        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
//        redisStandaloneConfiguration.setDatabase(properties.getDatabase());
//        redisStandaloneConfiguration.setHostName(properties.getHostName());
//        redisStandaloneConfiguration.setPort(properties.getPort());
//        redisStandaloneConfiguration.setPassword(RedisPassword.of(properties.getPassword()));
//        return redisStandaloneConfiguration;
//    }
//
//    @Bean
//    public JedisShardInfo jedisShardInfo(){
//        JedisShardInfo jedisShardInfo = new JedisShardInfo(properties.getHostName(), properties.getPort());
//        jedisShardInfo.setConnectionTimeout(properties.getTimeout());
//        jedisShardInfo.setPassword(properties.getPassword());
//        return jedisShardInfo;
//    }

//    @Bean
//    public JedisConnectionFactory jedisConnectionFactory(ResStandaloneConfiguration redisStandaloneConfiguration){
//        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisStandaloneConfiguration);
////        jedisConnectionFactory
//        return jedisConnectionFactory;
//    }

}
