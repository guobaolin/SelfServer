package com.gbl;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Consumer Application Boot
 */
@SpringBootApplication
@EnableDubboConfiguration
public class ConsumerApplicationBoot1 {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplicationBoot1.class, args);
    }
}
