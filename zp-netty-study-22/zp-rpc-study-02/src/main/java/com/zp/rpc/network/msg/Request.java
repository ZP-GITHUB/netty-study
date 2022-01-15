package com.zp.rpc.network.msg;

/**
 * @author ZP
 * @date 2022/1/15.
 */
public class Request {

    private String requestId;
    private Object result;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

}