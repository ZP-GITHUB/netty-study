package com.zp.rpc.network.msg;

/**
 * @author ZP
 * @date 2022/1/15.
 * 接收返回的结果
 */
public class Response {

    private String requestId;
    private String param;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

}