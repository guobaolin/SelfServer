package com.gbl.sentinel.client.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by guobaolin on 2019/7/11.
 */
@Slf4j
@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello(String name) {
        log.info("hello {}", name);
        return "hello " + name;
    }

}
