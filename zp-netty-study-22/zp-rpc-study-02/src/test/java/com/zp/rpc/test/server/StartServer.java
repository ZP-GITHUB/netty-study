package com.zp.rpc.test.server;

import com.zp.rpc.network.server.ServerSocket;

/**
 * @author ZP
 * @date 2022/1/15.
 */
public class StartServer {

    public static void main(String[] args) {
        System.out.println("启动服务端开始");
        new Thread(new ServerSocket()).start();
        System.out.println("启动服务端完成");
    }

}