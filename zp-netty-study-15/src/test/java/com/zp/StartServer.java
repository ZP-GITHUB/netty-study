package com.zp;

import com.zp.server.ServerSocket;

/**
 * @author ZP
 * @date 2021/12/25.
 */
public class StartServer {

    public static void main(String[] args) {
        new Thread(new ServerSocket()).start();
        System.out.println("itstack-demo-netty server start done. {关注公众号：bugstack虫洞栈，获取源码}");
    }

}