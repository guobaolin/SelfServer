package com.gbl.service;

import com.gbl.api.entity.DataType;
import com.gbl.api.service.RedisService;
import com.gbl.repository.DataTypeRepository;
import com.gbl.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.io.Serializable;

@Service(value = "redisService")
public class RedisServiceImpl implements RedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private DataTypeRepository dataTypeRepository;

    @Override
    public Serializable get(String key) {

        DataType dataType = dataTypeRepository.findByDataTypeId(Integer.valueOf(key));

        Integer i = dataTypeRepository.addDataType(dataType);

        return dataType.toString();
    }

    @Override
    public boolean set(String key, Serializable value) {
        stringRedisTemplate.opsForValue().set(key, (String) value);
        return true;
    }

    @Override
    public void remove(String key) {
        stringRedisTemplate.opsForValue().getOperations().delete(key);
    }

    @Override
    public boolean exists(String key) {
        return stringRedisTemplate.opsForValue().getOperations().hasKey(key);
    }

}

