package com.gbl.controller;

import com.gbl.api.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 80021687
 */
@RestController
public class RedisController {

    @Autowired
    @Qualifier("redisService")
    private RedisService redisService;

    @RequestMapping("/index")
    public String index(String name) {
        return "hello "+redisService.get(name);
    }
}
