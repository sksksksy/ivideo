package com.java.cache.self;

import com.java.cache.CacheOfSelf;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;

/**
 * 简单缓存类
 */
public class CacheOfObject implements CacheOfSelf {
    private CacheOfSimple cache = CacheOfSimple.takeCache();

    /**
     * 获取值T类型
     *
     * @param tClass
     * @param key
     * @return
     */
    @Override
    public <T extends Serializable> T getValue(Class<T> tClass, String key) {
        Unit unit = cache.getUnitByKey(key, key);
        if (unit == null) {
            return null;
        }
        T t = (T) unit.getValue();
        return t;
    }

    @Override
    public <T extends Serializable> T getValueOrDefault(Class<T> tClass, String key, T defaultValue) {
        T value = getValue(tClass, key);
        return value == null ? defaultValue : value;
    }

    /**
     * 设置T类型的值
     *
     * @param key
     * @param val
     */
    @Override
    public <T extends Serializable> void setKeyValue(String key, T val) {
        cache.put(key, val);
    }

    /**
     * 设置T类型的值，有超时
     *
     * @param key
     * @param val
     * @param expired
     */
    @Override
    public <T extends Serializable> void setKeyValue(String key, T val, int expired) {
        cache.putOfExpire(key, val, expired);
    }

    /**
     * 获取String类型的值
     *
     * @param key
     * @return
     */
    @Override
    public String getStringValue(String key) {
        Unit unitByKey = cache.getUnitByKey(key, key);
        return forString(unitByKey.getValue());
    }

    /**
     * 获取String值，为空时选择默认值
     *
     * @param key
     * @param defaultValue
     * @return
     */
    @Override
    public String getStringValueOrDefault(String key, String defaultValue) {
        Unit unitByKey = cache.getUnitByKey(key, key);
        if (unitByKey == null) {
            return defaultValue;
        } else {
            if (unitByKey.getValue() == null) {
                return defaultValue;
            } else {
                return forString(unitByKey.getValue());
            }
        }
    }

    @Override
    public void setKeyStringValue(String key, String value) {
        cache.put(key, value);
    }

    @Override
    public void setKeyStringValue(String key, String value, int expired) {
        cache.putOfExpire(key, value, expired);
    }

    /**
     * 转为字符串
     *
     * @param bs
     * @return
     */
    private String forString(byte[] bs) {
        return new String(bs, StandardCharsets.UTF_8);
    }
}
