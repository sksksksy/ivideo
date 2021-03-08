package com.java.cache.self;

import com.java.tool.ObjectTools;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 自己实现的简单缓存，可以设置超时
 */
public class CacheOfSimple {
    //用于缓存
    private final static ConcurrentHashMap<String, List<Unit>> caches = new ConcurrentHashMap<String, List<Unit>>();
    private ToByte toByte;

    private CacheOfSimple() {
        //启用超时监视
        CacheTask task = new CacheTask();
        task.monitor();
    }

    //懒加载单例-线程安全模式
    private static class CacheFactory {
        private final static CacheOfSimple INSTANCE = new CacheOfSimple();
    }

    /**
     * 拿缓存实例
     *
     * @return
     */
    public static CacheOfSimple takeCache() {
        //初始化转换器
        if (CacheFactory.INSTANCE.toByte == null) {
            CacheFactory.INSTANCE.toByte = (s) -> {
                if (s instanceof String) {
                    return ((String) s).getBytes(StandardCharsets.UTF_8);
                }
                return ObjectTools.toArray(s);
            };
        }
        return CacheFactory.INSTANCE;
    }

    /**
     * push 值
     *
     * @param key 缓存和存储单元的键值
     * @param val 需要存储的值
     */
    public <T extends Serializable> void put(String key, T val) {
        Objects.requireNonNull(val, "value can't be null");
        List<Unit> units = putOfCheck(key, key, val, -1);
        caches.put(key, units);
    }

    /**
     * push 值
     *
     * @param key1 缓存的键值
     * @param key2 存储单元的键值
     * @param val  需要存储的值
     */
    public <T extends Serializable> void putOfTwoKey(String key1, String key2, T val) {
        Objects.requireNonNull(val, "value can't be null");
        List<Unit> units = putOfCheck(key1, key2, val, -1);
        caches.put(key1, units);
    }

    /**
     * push 值
     *
     * @param key    缓存和存储单元的键值
     * @param val    需要存储的值
     * @param expire 超时 单位秒
     */
    public <T extends Serializable> void putOfExpire(String key, T val, int expire) {
        Objects.requireNonNull(val, "value can't be null");
        List<Unit> units = putOfCheck(key, key, val, expire);
        caches.put(key, units);
    }

    /**
     * 将key1，key2推入缓存，并设置超时
     *
     * @param key1
     * @param key2
     * @param val
     * @param expire
     * @param <T>
     */
    public <T extends Serializable> void putOfTwoExpire(String key1, String key2, T val, int expire) {
        Objects.requireNonNull(val, "value can't be null");
        List<Unit> units = putOfCheck(key1, key2, val, expire);
        caches.put(key1, units);
    }

    /**
     * 直接返回map<String,Unit>
     *
     * @return
     */
    public ConcurrentHashMap<String, List<Unit>> getCache() {
        return caches;
    }

    /**
     * 通过key1和key2直接获取unit
     *
     * @param key1
     * @param key2
     * @return
     */
    public Unit getUnitByKey(String key1, String key2) {
        List<Unit> units = caches.get(key1);
        if (units != null) {
            for (Unit u : units) {
                if (u.getKey().equals(key2)) {
                    return u;
                }
            }
        }
        return null;
    }

    /**
     * 根据key1,key2,和返回值类型获取返回值
     *
     * @param key1
     * @param key2
     * @param returnType
     * @param <T>
     * @return
     */
    public <T extends Serializable> T getValByKeyAndKey1(String key1, String key2, Class<T> returnType) {
        List<T> ts = new ArrayList<>(1);
        List<Unit> units = caches.get(key1);
        if (units != null) {
            units.stream().filter(u -> u.getKey().equals(key2)).forEach(e -> ts.add(0, (T) e.getValue()));
        }
        return ts.get(0);
    }

    /**
     * 清除所有
     */
    public void clear() {
        caches.clear();
    }

    /**
     * 插入数据
     *
     * @param key1
     * @param key2
     * @param val
     * @param expire
     * @param <T>
     * @return
     */
    private <T extends Serializable> List<Unit> putOfCheck(@Nonnull String key1, @Nonnull String key2, T val, int expire) {
        List<Unit> units = caches.get(key1);
        List<Unit> temp = new ArrayList<>(1);
        if (units == null) {
            units = new LinkedList<>();
        }
        units.stream().forEach(u -> {
            if (u.getValue().equals(key2)) {
                u.setValue(toByte.toByte(val));
            } else {
                u.setKey(key2);
                u.setValue(toByte.toByte(val));
                u.setExpire(expire);
                temp.add(u);
            }
        });
        units.addAll(temp);
        return units;
    }
}
