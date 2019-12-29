package com.gbl.txlcn.client1.service;

import org.springframework.stereotype.Service;

/**
 * Created by guobaolin on 2019/7/10.
 */
@Service
public interface BankService {

    int update(int fromId, int toId, int money);
}
