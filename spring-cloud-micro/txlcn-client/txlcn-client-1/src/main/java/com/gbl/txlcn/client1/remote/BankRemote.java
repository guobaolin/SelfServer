package com.gbl.txlcn.client1.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by guobaolin on 2019/7/10.
 */
@FeignClient(name = "txlcn-client2")
public interface BankRemote {

    /**
     * 转账给toId
     *
     * @param toId
     * @param money
     * @return
     */
    @RequestMapping("/update")
    int update(@RequestParam("toId") int toId, @RequestParam("money") int money);
}
