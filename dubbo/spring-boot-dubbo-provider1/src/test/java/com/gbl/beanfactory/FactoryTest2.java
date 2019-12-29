package com.gbl.beanfactory;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Controller;

import java.util.Arrays;

/**
 * Created by guobaolin on 2019/12/27.
 */
@Controller(value = "OK")
public class FactoryTest2 {

    /// BeanFactory的三个子接口：
    /// HierarchicalBeanFactory：提供父容器的访问功能
    /// ListableBeanFactory：提供了批量获取Bean的方法
    /// AutowireCapableBeanFactory：在BeanFactory基础上实现对已存在实例的管理

    /// ApplicationContext常用实现类:
    /// AnnotationConfigApplicationContext      从一个或多个基于java的配置类中加载上下文定义，适用于java注解的方式。
    /// ClassPathXmlApplicationContext          从类路径下的一个或多个xml配置文件中加载上下文定义，适用于xml配置的方式。
    /// FileSystemXmlApplicationContext         从文件系统下的一个或多个xml配置文件中加载上下文定义，也就是说系统盘符中加载xml配置文件。
    /// AnnotationConfigWebApplicationContext   专门为web应用准备的，适用于注解方式。
    /// XmlWebApplicationContext                从web应用下的一个或多个xml配置文件加载上下文定义，适用于xml配置方式。

    public static void main(String[] args) {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        RootBeanDefinition definition = new RootBeanDefinition(FactoryTest.class);
        factory.registerBeanDefinition("test", definition);

        System.out.println(
                Arrays.toString(
                        factory.getBeanNamesForAnnotation(Controller.class)));
        System.out.println(factory.getBeansWithAnnotation(Controller.class));
        System.out.println(factory.findAnnotationOnBean("test", Controller.class));
    }
}
