package com.gbl.beanfactory;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Created by guobaolin on 2019/12/27.
 */
public class FactoryTest implements FactoryBean<String> {

    public static void main(String[] args) {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        factory.registerBeanDefinition("test", new RootBeanDefinition(FactoryTest.class));
        System.out.println(factory.getBean("test"));
        System.out.println(factory.getBean("&test"));
    }

    @Override
    public String getObject() throws Exception {
        return "gbl";
    }

    @Override
    public Class<?> getObjectType() {
        return String.class;
    }
}
