package com.gbl.service;

import com.alibaba.dubbo.config.annotation.Service;
import org.apache.dubbo.demo.DemoService;
import org.springframework.stereotype.Component;

/**
 * Created by guobaolin on 2019/12/23.
 */
//@Service(
//        application = "dubbo-provider-id-1",
//        registry = "mv",
//        protocol = "protocol-1"
//)
@Service
@Component
public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
