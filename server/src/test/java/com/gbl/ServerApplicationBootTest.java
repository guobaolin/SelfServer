package com.gbl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServerApplicationBootTest {

    @Autowired
    JedisPool jedisPool;

    @Test
    public void test(){
        String uuid = UUID.randomUUID().toString();
        try (Jedis jedis = jedisPool.getResource()){
            jedis.set("uuid", uuid);
        }

    }
}
