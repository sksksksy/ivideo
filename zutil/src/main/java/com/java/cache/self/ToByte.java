package com.java.cache.self;

import java.io.Serializable;

@FunctionalInterface
public interface ToByte {
    byte[] toByte(Serializable t);
}
