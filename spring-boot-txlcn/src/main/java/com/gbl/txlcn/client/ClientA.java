package com.gbl.txlcn.client;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.stereotype.Service;

/**
 * Created by guobaolin on 2019/7/9.
 */
@Service
public class ClientA {

    @LcnTransaction
    public String execute(String value) {
        return "";
    }

}
