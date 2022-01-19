package com.zp.rpc.config;

/**
 * http://www.itstack.org
 * create by fuzhengwei on 2019/5/6
 */
public class ProviderConfig {

    protected String nozzle; //接口
    protected String ref;    //映射
    protected String alias;  //别名

    public String getNozzle() {
        return nozzle;
    }

    public void setNozzle(String nozzle) {
        this.nozzle = nozzle;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
