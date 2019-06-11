package com.gbl.controller;

import com.gbl.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by guobaolin on 2019/2/23.
 */
@RestController
public class IndexController {

    @Autowired
    private IndexService indexService;

    @RequestMapping(value = "index")
    public String index(String name){
        return indexService.getIndex(name);
    }
}
