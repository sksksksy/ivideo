package com.java.exception;

import java.util.function.Predicate;

/**
 * 自定义运行时错误
 */
public class BaseRunException extends RuntimeException {
    /**
     * 错误码
     */
    private Integer code;
    static final long serialVersionUID = 1L;

    public BaseRunException() {
        super();
    }


    public BaseRunException(Integer code, String message) {
        super(assembly(message, code));
    }

    public BaseRunException(Integer code, String message, Throwable cause) {
        super(assembly(message, code), cause);
    }

    public BaseRunException(Throwable cause) {
        super(cause);
    }

    protected BaseRunException(Integer code, String message, Throwable cause,
                               boolean enableSuppression,
                               boolean writableStackTrace) {
        super(assembly(message, code), cause, enableSuppression, writableStackTrace);
    }

    protected static String assembly(String message, Integer code) {
        return code != null ? code + "-" + message : "0000-" + message;
    }

    /**
     * 普通抛出异常
     *
     * @param code
     * @param message
     */
    public static void throwException(Integer code, String message) {
        throw new BaseRunException(code, message);
    }

    public static void throwException(String message) {
        throwException(null, message);
    }

    /**
     * 普通抛出异常
     *
     * @param code
     * @param message
     * @param cause
     */
    public static void throwException(Integer code, String message, Throwable cause) {
        throw new BaseRunException(code, message, cause);
    }

    /**
     * 抛出异常
     *
     * @param message
     * @param cause
     */
    public static void throwException(String message, Throwable cause) {
        throwException(null, message, cause);
    }

    /**
     * 检测是否为空，若为空则抛出异常
     *
     * @param obj
     * @param message
     */
    public static void NotNull(Object obj, String message) {
        if (obj == null) {
            throwException(message);
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

    public Integer getCode() {
        return this.code;
    }
}
