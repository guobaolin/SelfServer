package com.gbl.test;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.context.support.XmlWebApplicationContext;

/**
 * Created by guobaolin on 2018/11/17.
 */
public class Test {

    @org.junit.Test
    public void resourceTest (){

        Resource resource = new ClassPathResource("");

        XmlWebApplicationContext xwc = new XmlWebApplicationContext();
        xwc.refresh();

    }
}
