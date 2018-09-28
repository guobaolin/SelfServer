package com.gbl.stereotype;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@com.alibaba.dubbo.config.annotation.Service
@Component
public @interface Service {
    @AliasFor(annotation = Component.class)
    String value() default "";

    @AliasFor(annotation = com.alibaba.dubbo.config.annotation.Service.class)
    Class<?> interfaceClass() default void.class;

    @AliasFor(annotation = com.alibaba.dubbo.config.annotation.Service.class)
    String interfaceName() default "";

    @AliasFor(annotation = com.alibaba.dubbo.config.annotation.Service.class)
    String version() default "";

    @AliasFor(annotation = com.alibaba.dubbo.config.annotation.Service.class)
    String group() default "";

    @AliasFor(annotation = com.alibaba.dubbo.config.annotation.Service.class)
    String path() default "";

    @AliasFor(annotation = com.alibaba.dubbo.config.annotation.Service.class)
    boolean export() default false;

    @AliasFor(annotation = com.alibaba.dubbo.config.annotation.Service.class)
    String token() default "";

    @AliasFor(annotation = com.alibaba.dubbo.config.annotation.Service.class)
    boolean deprecated() default false;

    @AliasFor(annotation = com.alibaba.dubbo.config.annotation.Service.class)
    boolean dynamic() default false;

    @AliasFor(annotation = com.alibaba.dubbo.config.annotation.Service.class)
    String accesslog() default "";

    @AliasFor(annotation = com.alibaba.dubbo.config.annotation.Service.class)
    int executes() default 0;

    @AliasFor(annotation = com.alibaba.dubbo.config.annotation.Service.class)
    boolean register() default false;

    @AliasFor(annotation = com.alibaba.dubbo.config.annotation.Service.class)
    int weight() default 0;

    @AliasFor(annotation = com.alibaba.dubbo.config.annotation.Service.class)
    String document() default "";

    @AliasFor(annotation = com.alibaba.dubbo.config.annotation.Service.class)
    int delay() default 0;

    @AliasFor(annotation = com.alibaba.dubbo.config.annotation.Service.class)
    String local() default "";

    @AliasFor(annotation = com.alibaba.dubbo.config.annotation.Service.class)
    String stub() default "";

    @AliasFor(annotation = com.alibaba.dubbo.config.annotation.Service.class)
    int timeout() default 0;



}
