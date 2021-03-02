package com.java.cache.self;

import com.java.cache.CacheOfSelf;

public class CacheServer implements CacheOfSelf {
    private ICache cache = ICache.takeCache();

    /**
     * 获取值T类型
     *
     * @param tClass
     * @param key
     * @return
     */
    @Override
    public <T> T getValue(Class<T> tClass, String key) {
        Unit unit = cache.getUnitByKey(key, key);
        if (unit == null) {
            return null;
        }
        T t = (T) unit.getValue();
        return t;
    }

    @Override
    public <T> T getValueOrDefault(Class<T> tClass, String key, T defaultValue) {
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
    public <T> void setKeyValue(String key, T val) {
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
    public <T> void setKeyValue(String key, T val, int expired) {
        cache.putOfExpire(key, val, expired);
    }
}
