package com.gbl.txlcn.server;

import com.codingapi.txlcn.tm.config.EnableTransactionManagerServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by guobaolin on 2019/7/10.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableTransactionManagerServer
public class TcManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TcManagerApplication.class);
    }
}
