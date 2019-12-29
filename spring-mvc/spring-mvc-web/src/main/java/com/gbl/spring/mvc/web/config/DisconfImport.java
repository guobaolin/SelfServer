package com.gbl.spring.mvc.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by guobaolin on 2019/11/19.
 */
@Configuration
@ImportResource("classpath:disconf.xml")
public class DisconfImport {

    public DisconfImport() {
        System.out.println("导入配置文件：disconf.xml");
    }
}
