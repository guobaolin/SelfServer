package com.gbl.autoconfigure;

import com.gbl.api.entity.DataType;
import com.gbl.service.DemoServiceImpl;
import org.apache.dubbo.demo.DemoService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;

/**
 * Created by guobaolin on 2019/12/23.
 */
//@Configuration
public class Configuration1 {

    @Bean
    @ConditionalOnBean(DataType.class)
    public DemoService demoService() {
        System.out.println("demoService bean create success");
        return new DemoServiceImpl();
    }
}
