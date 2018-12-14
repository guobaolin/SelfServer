package com.gbl.controller;

import com.gbl.entity.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by guobaolin on 2018/11/15.
 */
@RestController
public class HomeController {

    @RequestMapping(value = "index",method = RequestMethod.POST ,consumes = "application/json", produces="text/html;charset=UTF-8")
    public String home(@RequestBody User user) {
        return user.toString();
    }
}
