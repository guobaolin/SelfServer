package com.gbl.controller;

import com.gbl.utils.TraceUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by guobaolin on 2019/3/14.
 */
@RestController
public class HelloController {

    @RequestMapping(value = "hello")
    public String hello(){
        String traceId = TraceUtil.getTraceId();
        return traceId;
    }

}
