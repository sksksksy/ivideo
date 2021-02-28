package com.java.cache.self;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 自己实现的简单缓存，可以设置超时
 */
public class ICache {
    //用于缓存
    private final static ConcurrentHashMap<String, Unit> caches = new ConcurrentHashMap<String, Unit>();

    private ICache() {
        //启用超时监视
        CacheTask task = new CacheTask();
        task.monitor();
    }

    //懒加载单例-线程安全模式
    private static class CacheFactory {
        private final static ICache INSTANCE = new ICache();
    }

    public static ICache takeCache() {
        return CacheFactory.INSTANCE;
    }

    /**
     * push 值
     *
     * @param key 缓存和存储单元的键值
     * @param val 需要存储的值
     */
    public void put(String key, String val) {
        Unit u = UnitFactory.getInstance();
        u.setKey(key);
        u.setValue(val);
        caches.put(key, u);
    }

    /**
     * push 值
     *
     * @param key1 缓存的键值
     * @param key2 存储单元的键值
     * @param val  需要存储的值
     */
    public void put(String key1, String key2, String val) {
        Unit u = UnitFactory.getInstance();
        u.setKey(key2);
        u.setValue(val);
        caches.put(key1, u);
    }

    /**
     * push 值
     *
     * @param key    缓存和存储单元的键值
     * @param val    需要存储的值
     * @param expire 超时 单位秒
     */
    public void put(String key, String val, int expire) {
        Unit u = UnitFactory.getInstance();
        u.setKey(key);
        u.setValue(val);
        u.setExpire(expire);
        caches.put(key, u);
    }

    public void put(String key1, String key2, String val, int expire) {
        Unit u = UnitFactory.getInstance();
        u.setKey(key2);
        u.setValue(val);
        u.setExpire(expire);
        caches.put(key1, u);
    }

    public ConcurrentHashMap<String, Unit> getCache() {
        return caches;
    }

    public void clear() {
        caches.clear();
    }
}
