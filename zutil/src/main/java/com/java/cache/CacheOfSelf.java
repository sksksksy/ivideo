package com.java.cache;

import java.io.Serializable;

/**
 * 缓存方法
 */
public interface CacheOfSelf {
    /**
     * 获取值T类型
     *
     * @param tClass
     * @param key
     * @param <T>
     * @return
     */
    <T extends Serializable> T getValue(Class<T> tClass, String key);

    <T extends Serializable> T getValueOrDefault(Class<T> tClass, String key, T defaultValue);

    /**
     * 设置T类型的值
     *
     * @param key
     * @param val
     * @param <T>
     */
    <T extends Serializable> void setKeyValue(String key, T val);

    /**
     * 设置T类型的值，有超时
     *
     * @param key
     * @param val
     * @param expired
     * @param <T>
     */
    <T extends Serializable> void setKeyValue(String key, T val, int expired);
    /**
     * 获取String类型的值
     * @param key
     * @return
     */
    String getStringValue(String key);

    String getStringValueOrDefault(String key, String defaultValue);

    void setKeyStringValue(String key, String value);

    void setKeyStringValue(String key, String value, int expired);
}