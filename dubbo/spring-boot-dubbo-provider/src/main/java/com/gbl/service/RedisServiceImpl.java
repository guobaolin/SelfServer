package com.gbl.service;

import com.gbl.api.service.RedisService;
import com.gbl.stereotype.Service;

import java.io.Serializable;

@Service(value = "redisService")
public class RedisServiceImpl implements RedisService {

    @Override
    public Serializable get(String key) {
        return key;
    }

    @Override
    public boolean set(String key, Serializable value) {
        return false;
    }

    @Override
    public void remove(String key) {

    }

    @Override
    public boolean exists(String key) {
        return false;
    }

}

