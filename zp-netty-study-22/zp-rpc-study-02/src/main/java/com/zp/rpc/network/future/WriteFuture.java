package com.zp.rpc.network.future;

import com.zp.rpc.network.msg.Response;
import io.netty.util.concurrent.Future;

/**
 * @author ZP
 * @date 2022/1/15.
 */
public interface WriteFuture<T> extends Future<T> {

    Throwable cause();

    void setCause(Throwable cause);

    boolean isWriteSuccess();

    void setWriteResult(boolean result);

    String requestId();

    T response();

    void setResponse(Response response);

    boolean isTimeout();


}