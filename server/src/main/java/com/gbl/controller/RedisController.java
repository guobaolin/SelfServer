package com.gbl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by guobaolin on 2018/10/29.
 */
//@RestController
public class RedisController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/set")
    public String set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
        return "success";
    }

    @RequestMapping("/hset")
    public String hset(String key, String hkey, String hvalue) {
        stringRedisTemplate.opsForHash().put(key, hkey, hvalue);
        return "success";
    }

    @RequestMapping("/lpush")
    public String leftPush(String key, String value){
        stringRedisTemplate.opsForList().leftPush(key, value);
        return "success";
    }

    @RequestMapping("/rpush")
    public String rightPush(String key, String value){
        stringRedisTemplate.opsForList().rightPush(key, value);
        return "success";
    }

    @RequestMapping("/range")
    public List<String> range(String key){
        List<String> stringList = stringRedisTemplate.opsForList().range(key, 0, -1);
        return stringList;
    }

}
