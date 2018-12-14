package com.gbl.controller;

import com.gbl.properties.RedisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by guobaolin on 2018/10/31.
 */
@RestController
@RequestMapping("/disconf/redis")
public class DisconfRedisController {

    @Autowired
    private RedisProperties properties;

    @RequestMapping("/getNewData")
    public String getNewData(){
        String hostName = properties.getHostName();
        int port = properties.getPort();
        return hostName + ";" + port;
    }
}
