package com.zp.rpc;

/**
 * @author ZP
 * @date 2022/1/18.
 */
public class ApiTest {
    public static void main(String[] args) {
        String[] configs = {"itstack-rpc-center.xml", "itstack-rpc-provider.xml", "itstack-rpc-consumer.xml"};
        new ClassPathXmlApplicationContext(configs);
    }
}
