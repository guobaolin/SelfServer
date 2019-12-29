package com.gbl.txlcn;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by guobaolin on 2019/7/9.
 */
@SpringBootApplication
@EnableDistributedTransaction
public class TXLCNApplication {

    public static void main(String[] args) {
        SpringApplication.run(TXLCNApplication.class);
    }
}
