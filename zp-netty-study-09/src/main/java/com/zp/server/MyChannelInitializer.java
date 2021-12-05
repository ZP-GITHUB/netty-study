package com.zp.server;

import com.zp.codec.MyDecoder;
import com.zp.codec.MyEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @author ZP
 * @date 2021/12/5.
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) {
        //自定义解码器
        channel.pipeline().addLast(new MyDecoder());
        //自定义编码器
        channel.pipeline().addLast(new MyEncoder());
        //在管道中添加我们自己的接收数据实现方法
        channel.pipeline().addLast(new MyServerHandler());
    }

}