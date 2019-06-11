package com.gbl.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gbl.api.service.RedisService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {

    @Reference
    private RedisService redisService;

    @RequestMapping("/index")
    public String index(String name) {
        return (String) redisService.get(name);
    }
}
