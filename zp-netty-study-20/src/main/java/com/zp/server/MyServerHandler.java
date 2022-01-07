package com.zp.server;

import com.zp.server.common.MyServerCommonHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelProgressiveFuture;

import java.util.function.Consumer;

/**
 * 虫洞栈：https://bugstack.cn
 * 公众号：bugstack虫洞栈 | 欢迎关注并获取专题&源码
 * Create by fuzhengwei on 2019
 */
public class MyServerHandler extends MyServerCommonHandler {

    @Override
    protected void sendData(ChannelHandlerContext ctx) {
        sentFlag = true;
        ctx.writeAndFlush( "111111111122222222223333333333\r\n", getChannelProgressivePromise(ctx, new Consumer<ChannelProgressiveFuture>() {
            @Override
            public void accept(ChannelProgressiveFuture channelProgressiveFuture) {
                if (ctx.channel().isWritable() && !sentFlag) {
                    sendData(ctx);
                }
            }
        }));
    }

}
