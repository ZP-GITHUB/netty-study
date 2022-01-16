package com.zp.rpc.network.future;

import com.zp.rpc.network.msg.Request;
import com.zp.rpc.network.msg.Response;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author ZP
 * @date 2022/1/15.
 */
public class SyncWrite {

    /**
     * 发送请求
     * @param channel
     * @param request
     * @param timeout
     * @return
     * @throws Exception
     */
    public Response writeAndSync(final Channel channel, final Request request, final long timeout) throws Exception {

        if (channel == null) {
            throw new NullPointerException("channel");
        }
        if (request == null) {
            throw new NullPointerException("request");
        }
        if (timeout <= 0) {
            throw new IllegalArgumentException("timeout <= 0");
        }

        String requestId = UUID.randomUUID().toString();
        request.setRequestId(requestId);

        WriteFuture<Response> future = new SyncWriteFuture(request.getRequestId());
        SyncWriteMap.syncKey.put(request.getRequestId(), future);

        Response response = doWriteAndSync(channel, request, timeout, future);

        SyncWriteMap.syncKey.remove(request.getRequestId());
        return response;
    }

    /**
     * 通过netty发送请求
     * @param channel
     * @param request
     * @param timeout
     * @param writeFuture
     * @return
     * @throws Exception
     */
    private Response doWriteAndSync(final Channel channel, final Request request, final long timeout, final WriteFuture<Response> writeFuture) throws Exception {

        channel.writeAndFlush(request).addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                writeFuture.setWriteResult(future.isSuccess());
                writeFuture.setCause(future.cause());
                //失败移除
                if (!writeFuture.isWriteSuccess()) {
                    SyncWriteMap.syncKey.remove(writeFuture.requestId());
                }
            }
        });
        /**设置超时时间*/
        Response response = writeFuture.get(timeout, TimeUnit.MILLISECONDS);
        if (response == null) {
            if (writeFuture.isTimeout()) {
                throw new TimeoutException();
            } else {
                // write exception
                throw new Exception(writeFuture.cause());
            }
        }
        return response;
    }

}