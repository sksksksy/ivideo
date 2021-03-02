package com.java.cache.self;

public class UnitFactory {

    /**
     * @return
     */
    public final static <T> Unit getInstance(Class<T> tClass) {
        return new Unit<T>();
    }
}
