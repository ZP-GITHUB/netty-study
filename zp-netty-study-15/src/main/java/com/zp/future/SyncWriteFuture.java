package com.zp.future;

import com.zp.msg.Response;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 虫洞栈：https://bugstack.cn
 * 公众号：bugstack虫洞栈  ｛关注获取学习源码｝
 * 虫洞群：①群5398358 ②群5360692
 * Create by fuzhengwei on 2019
 */
public class SyncWriteFuture implements WriteFuture<Response> {

    private CountDownLatch latch = new CountDownLatch(1);
    private final long begin = System.currentTimeMillis();
    private long timeout;
    private Response response;
    private final String requestId;
    private boolean writeResult;
    private Throwable cause;
    private boolean isTimeout = false;

    public SyncWriteFuture(String requestId) {
        this.requestId = requestId;
    }

    public SyncWriteFuture(String requestId, long timeout) {
        this.requestId = requestId;
        this.timeout = timeout;
        writeResult = true;
        isTimeout = false;
    }


    @Override
    public Throwable cause() {
        return cause;
    }

    @Override
    public void setCause(Throwable cause) {
        this.cause = cause;
    }

    @Override
    public boolean isWriteSuccess() {
        return writeResult;
    }

    @Override
    public void setWriteResult(boolean result) {
        this.writeResult = result;
    }

    @Override
    public String requestId() {
        return requestId;
    }

    @Override
    public Response response() {
        return response;
    }

    @Override
    public void setResponse(Response response) {
        this.response = response;
        latch.countDown();
    }

    public boolean cancel(boolean mayInterruptIfRunning) {
        return true;
    }

    public boolean isCancelled() {
        return false;
    }

    public boolean isDone() {
        return false;
    }

    public Response get() throws InterruptedException, ExecutionException {
        latch.wait();
        return response;
    }

    public Response get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        if (latch.await(timeout, unit)) {
            return response;
        }
        return null;
    }

    @Override
    public boolean isTimeout() {
        if (isTimeout) {
            return isTimeout;
        }
        return System.currentTimeMillis() - begin > timeout;
    }
}
