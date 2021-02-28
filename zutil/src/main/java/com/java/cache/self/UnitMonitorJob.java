package com.java.cache.self;

import java.util.concurrent.ConcurrentHashMap;

public class UnitMonitorJob implements Runnable {
    @Override
    public void run() {
        ICache cache = ICache.takeCache();
        //超时移除
        ConcurrentHashMap<String, Unit> cacheMap = cache.getCache();
        cacheMap.forEach((k, v) -> {
            if (v.expired()) {
                cacheMap.remove(k);
            }
        });
    }

}
