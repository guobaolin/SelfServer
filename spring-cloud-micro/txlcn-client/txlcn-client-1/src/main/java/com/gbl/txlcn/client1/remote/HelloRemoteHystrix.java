package com.gbl.txlcn.client1.remote;

import org.springframework.stereotype.Component;

/**
 * Created by guobaolin on 2019/7/10.
 */
@Component
public class HelloRemoteHystrix implements HelloRemote {


    @Override
    public String hello(String name) {
        return "hello " + name + ", this is message send failed ";
    }
}
