package com.gbl.txlcn.client2;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by guobaolin on 2019/7/10.
 */
@SpringBootApplication
@EnableFeignClients
//@EnableCircuitBreaker
@EnableDiscoveryClient
@MapperScan("com.gbl.txlcn.client2.dao")
@EnableDistributedTransaction
@EnableTransactionManagement
public class TcClient2Application {

    public static void main(String[] args) {
        SpringApplication.run(TcClient2Application.class);
    }
}
