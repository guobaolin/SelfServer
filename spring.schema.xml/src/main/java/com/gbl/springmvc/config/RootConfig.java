package com.gbl.springmvc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by guobaolin on 2019/2/23.
 */
@Configuration
@ComponentScan(basePackages = {"com.gbl"}, excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)})
@ImportResource(locations={"classpath:spring-application.xml"})
public class RootConfig{

}
