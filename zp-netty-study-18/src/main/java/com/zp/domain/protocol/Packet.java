package com.zp.domain.protocol;

/**
 * @author ZP
 * @date 2022/1/3.
 */
public abstract class Packet {

    /**
     * 获取协议指令
     * @return 返回指令值
     * 定义协议包头，所以的通信消息都继承这个类
     */
    public abstract Byte getCommand();

}