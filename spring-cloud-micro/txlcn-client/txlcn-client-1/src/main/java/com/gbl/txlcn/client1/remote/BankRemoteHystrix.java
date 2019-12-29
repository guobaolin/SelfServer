package com.gbl.txlcn.client1.remote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by guobaolin on 2019/7/10.
 */
@Component
public class BankRemoteHystrix implements BankRemote {

    private static final Logger LOGGER = LoggerFactory.getLogger(BankRemoteHystrix.class);

    @Override
    public int update(int toId, int money) {
        LOGGER.warn("远程转账失败，进行熔断操作");
        throw new RuntimeException();
    }
}
