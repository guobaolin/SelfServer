package com.gbl.applicationcontext;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by guobaolin on 2019/12/28.
 */
public class ApplicationContextTest {

    @Test
    public void test() {
        ApplicationContext context = new AnnotationConfigApplicationContext(ManConfig.class);
        Man man = context.getBean(Man.class);
    }
}
