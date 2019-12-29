package com.gbl.spring.mvc.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by guobaolin on 2019/11/18.
 */
@RestController
public class IndexController {

    @Value("${disconf:null}")
    private String disconf;

    @RequestMapping(value = "/index/{name}")
    public String index(@PathVariable("name") String name) {
        return "Hello " + name + ", disconf is " + disconf;
    }
}
