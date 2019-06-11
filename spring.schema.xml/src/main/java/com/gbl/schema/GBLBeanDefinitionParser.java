package com.gbl.schema;

import com.gbl.entity.Student;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * Created by guobaolin on 2019/2/23.
 */
public class GBLBeanDefinitionParser implements BeanDefinitionParser {

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        RootBeanDefinition def = new RootBeanDefinition();

        // 设置Bean Class
        def.setBeanClass(Student.class);

        // 注册ID属性
        String beanId = element.getAttribute("id");
        BeanDefinitionHolder beanIdHolder = new BeanDefinitionHolder(def, beanId);
        BeanDefinitionReaderUtils.registerBeanDefinition(beanIdHolder, parserContext.getRegistry());

        // 注册属性
        String id = element.getAttribute("identity");
        String name = element.getAttribute("param1");
        String age = element.getAttribute("param2");
        String company = element.getAttribute("param3");

        BeanDefinitionHolder idHolder = new BeanDefinitionHolder(def, id);
        BeanDefinitionHolder nameHolder = new BeanDefinitionHolder(def, name);
        BeanDefinitionHolder ageHolder = new BeanDefinitionHolder(def, age);
        BeanDefinitionHolder companyHolder = new BeanDefinitionHolder(def, company);

        BeanDefinitionReaderUtils.registerBeanDefinition(idHolder, parserContext.getRegistry());
        BeanDefinitionReaderUtils.registerBeanDefinition(nameHolder, parserContext.getRegistry());
        BeanDefinitionReaderUtils.registerBeanDefinition(ageHolder, parserContext.getRegistry());
        BeanDefinitionReaderUtils.registerBeanDefinition(companyHolder, parserContext.getRegistry());

        def.getPropertyValues().addPropertyValue("studentId", id);
        def.getPropertyValues().addPropertyValue("name", name);
        def.getPropertyValues().addPropertyValue("age", age);
        def.getPropertyValues().addPropertyValue("company", company);

        return def;
    }
}
