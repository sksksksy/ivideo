package com.java.cache.self;

import java.io.Serializable;

/**
 * 缓存存储的基本单元
 *
 * @author ZHP
 */
public class Unit implements Serializable {

    private static final long serialVersionUID = 1L;

    private String key;
    private String value;
    private int expire = 0;
    //unit second
    private transient long startTime = 0L;

    public Unit() {
        startTime = System.currentTimeMillis() / 1000;
    }

    /**
     * 判断数据是否超时
     *
     * @return
     */
    public boolean expired() {
        if (expire <= 0) return true;
        long nowTime = System.currentTimeMillis();
        return (nowTime / 1000 - startTime) >= expire;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
//		long nowTime=System.currentTimeMillis();
//		this.startTime=nowTime/1000;
        this.expire = expire;
    }

    @Override
    public String toString() {
        return "Unit [key=" + key + ", value=" + value + ", expire=" + expire + ", startTime=" + startTime + "]";
    }

}
