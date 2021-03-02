package com.java.cache;

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
    <T> T getValue(Class<T> tClass, String key);

    <T> T getValueOrDefault(Class<T> tClass, String key, T defaultValue);

    /**
     * 设置T类型的值
     *
     * @param key
     * @param val
     * @param <T>
     */
    <T> void setKeyValue(String key, T val);

    /**
     * 设置T类型的值，有超时
     *
     * @param key
     * @param val
     * @param expired
     * @param <T>
     */
    <T> void setKeyValue(String key, T val, int expired);

}