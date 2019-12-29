package com.gbl.txlcn.client1.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.gbl.txlcn.client1.dao.BankDao;
import com.gbl.txlcn.client1.remote.BankRemote;
import com.gbl.txlcn.client1.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by guobaolin on 2019/7/10.
 */
@Service
public class BankServiceImpl implements BankService {

    @Autowired
    private BankRemote bankRemote;

    @Autowired
    private BankDao bankDao;

    @LcnTransaction
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public int update(int fromId, int toId, int money) {
        int to = bankRemote.update(toId, money);
        int from = bankDao.update(fromId, money);
        throw new RuntimeException();
//        return from + to;
    }
}
