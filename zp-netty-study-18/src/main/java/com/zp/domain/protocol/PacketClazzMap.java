package com.zp.domain.protocol;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ZP
 * @date 2022/1/3.
 */
public class PacketClazzMap {

    public final static Map<Byte, Class<? extends Packet>> packetTypeMap = new ConcurrentHashMap<>();

    static {
        packetTypeMap.put(Command.Demo01, MsgDemo01.class);
        packetTypeMap.put(Command.Demo02, MsgDemo02.class);
        packetTypeMap.put(Command.Demo03, MsgDemo03.class);
    }

}