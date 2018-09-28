package com.gbl;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Consumer Application Boot
 */
@SpringBootApplication
@DubboComponentScan
public class ConsumerApplicationBoot {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplicationBoot.class, args);
    }
}
