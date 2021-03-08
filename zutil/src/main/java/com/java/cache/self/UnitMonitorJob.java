package com.java.cache.self;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class UnitMonitorJob implements Runnable {
    @Override
    public void run() {
        CacheOfSimple cache = CacheOfSimple.takeCache();
        //超时移除
        ConcurrentHashMap<String, List<Unit>> cacheMaps = cache.getCache();
        cacheMaps.forEach((k, v) -> {
            v.stream().forEach(e -> {
                if (e.expired()) {
                    v.remove(e);
                }
            });
            if (v.isEmpty()) {
                cacheMaps.remove(v);
            }
        });
    }

}
