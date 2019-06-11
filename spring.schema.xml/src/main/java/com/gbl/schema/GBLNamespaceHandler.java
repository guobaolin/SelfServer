package com.gbl.schema;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Created by guobaolin on 2019/2/23.
 */
public class GBLNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("entity", new GBLBeanDefinitionParser());
    }
}
