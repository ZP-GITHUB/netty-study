package com.zp.rpc.config.spring;

import com.zp.rpc.config.spring.bean.ConsumerBean;
import com.zp.rpc.config.spring.bean.ProviderBean;
import com.zp.rpc.config.spring.bean.ServerBean;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * http://www.itstack.org
 * create by fuzhengwei on 2019/5/6
 */
public class MyNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("consumer", new MyBeanDefinitionParser(ConsumerBean.class));
        registerBeanDefinitionParser("provider", new MyBeanDefinitionParser(ProviderBean.class));
        registerBeanDefinitionParser("server", new MyBeanDefinitionParser(ServerBean.class));
    }

}
