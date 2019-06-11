package com.gbl;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Provider Application Boot
 */
@SpringBootApplication
@EnableDubboConfiguration
public class ProviderApplicationBoot3 {

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplicationBoot3.class, args);
    }
}
