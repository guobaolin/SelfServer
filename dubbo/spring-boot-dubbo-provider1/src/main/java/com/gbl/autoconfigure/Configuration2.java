package com.gbl.autoconfigure;

import com.gbl.api.entity.DataType;
import org.springframework.context.annotation.Bean;

/**
 * Created by guobaolin on 2019/12/23.
 */
//@Configuration
public class Configuration2 {

    @Bean
    public DataType dataType() {
        System.out.println("dataType bean create success");
        return new DataType();
    }
}
