package com.gbl;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Provider Application Boot
 */
@SpringBootApplication
//@EnableDubboConfiguration
@DubboComponentScan
@EnableDubboConfig
public class ProviderApplicationBoot1 {

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplicationBoot1.class, args);
    }
}
