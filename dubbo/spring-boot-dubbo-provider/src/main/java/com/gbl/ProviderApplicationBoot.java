package com.gbl;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Provider Application Boot
 */
@SpringBootApplication
//@DubboComponentScan
@EnableDubboConfiguration
public class ProviderApplicationBoot {

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplicationBoot.class, args);
    }
}
