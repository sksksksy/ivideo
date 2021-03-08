package com.java.exception;

import java.util.function.Predicate;

/**
 * 断言处理
 */
public class Asserts {
    /**
     * 检测是否为空，若为空则抛出异常
     *
     * @param obj
     * @param message
     */
    public static void NotNull(Object obj, String message) {
        if (obj == null) {
            BaseRunException.throwException(message);
        }
    }

    /**
     * 判断，如果传入的status为ture则抛出异常
     *
     * @param status
     * @param code
     * @param message
     */
    public static void check(boolean status, Integer code, String message) {
        if (status) {
            throw new BaseRunException(code, message);
        }
    }

    public static void check(boolean status, String message) {
        check(status, null, message);
    }

    /**
     * predicate返回true则不抛出异常,否则抛出异常
     *
     * @param predicate
     * @param param
     * @param code
     * @param message
     * @param <T>
     */
    public static <T> void check(Predicate<T> predicate, T param, Integer code, String message) {
        check(!predicate.test(param), code, message);
    }

    public static <T> void check(Predicate<T> predicate, T param, String message) {
        check(!predicate.test(param), null, message);
    }
}
