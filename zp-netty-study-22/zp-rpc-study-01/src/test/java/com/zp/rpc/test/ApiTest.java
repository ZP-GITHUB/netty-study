package com.zp.rpc.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author ZP
 * @date 2022/1/12.
 */
public class ApiTest {

    public static void main(String[] args) {
        String[] configs = {"itstack-rpc-consumer.xml", "itstack-rpc-provider.xml"};
        new ClassPathXmlApplicationContext(configs);
    }

}