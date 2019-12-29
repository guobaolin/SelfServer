package com.gbl.beandefinition;

import com.gbl.service.DemoServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;

/**
 * Created by guobaolin on 2019/12/26.
 */
public class BeanDefinitionTest {

    @Test
    public void test1() {
        Class<?> clazz = DemoServiceImpl.class;

//        ClassPathXmlApplicationContext

        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
        AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();

    }
}
