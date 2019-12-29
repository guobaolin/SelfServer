package com.gbl.txlcn.client1.controller;

import com.gbl.txlcn.client1.remote.HelloRemote;
import com.gbl.txlcn.client1.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by guobaolin on 2019/7/10.
 */
@RestController
public class ConsumerController {

    @Resource
    private HelloRemote helloRemote;

    @Autowired
    private BankService bankService;

    @RequestMapping("/hello/{name}")
    public String index(@PathVariable("name") String name) {
        return helloRemote.hello(name);
    }

    @RequestMapping("/transfer/{fromId}/{toId}/{money}")
    public String transfer(@PathVariable int fromId, @PathVariable int toId, @PathVariable int money) {
        int result = bankService.update(fromId, toId, money);
        return result > 0 ? "转账成功" : "转账失败";
    }
}
