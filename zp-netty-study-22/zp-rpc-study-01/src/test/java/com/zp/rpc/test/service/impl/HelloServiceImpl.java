package com.zp.rpc.test.service.impl;


import com.zp.rpc.test.service.HelloService;

/**
 * http://www.itstack.org
 * create by fuzhengwei on 2019/5/6
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public void echo() {
        System.out.println("hi itstack demo rpc");
    }

}
