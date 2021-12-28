package com.zp.domain;

/**
 * @author ZP
 * @date 2021/12/28.
 */
public class MsgAgreement {

    private String toChannelId; //发送给某人，某人channelId
    private String content;     //消息内容

    public MsgAgreement() {
    }

    public MsgAgreement(String toChannelId, String content) {
        this.toChannelId = toChannelId;
        this.content = content;
    }

    public String getToChannelId() {
        return toChannelId;
    }

    public void setToChannelId(String toChannelId) {
        this.toChannelId = toChannelId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}