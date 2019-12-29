package com.gbl;

import com.gbl.api.service.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;

@RunWith(SpringRunner.class)
public class ProviderApplicationBootTest {

    @Autowired
    @Qualifier("RedisServiceImpl")
    private RedisService redisService;

    @Test
    public void index(){
        Serializable id = redisService.get("name");
        // TODO
        System.out.println(id);
    }
}
