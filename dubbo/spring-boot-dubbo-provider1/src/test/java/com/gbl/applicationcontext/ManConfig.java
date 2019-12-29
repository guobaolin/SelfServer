package com.gbl.applicationcontext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by guobaolin on 2019/12/28.
 */
@Configuration
public class ManConfig {

    @Bean
    public Man man() {
        return new Man();
    }
}
