package com.gbl.txlcn.client2.controlller;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.gbl.txlcn.client2.dao.BankDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by guobaolin on 2019/7/10.
 */
@RestController
public class ConsumerController {

    @Autowired
    private BankDao bankDao;

    @RequestMapping("/hello")
    public String hello(String name) {
        return "hello " + name;
    }

    @RequestMapping("/update")
    @LcnTransaction
    public int update(int toId, int money) {
        int result = bankDao.update(toId, money);
        return result;
    }
}
