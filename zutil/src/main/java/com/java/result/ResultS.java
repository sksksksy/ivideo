package com.java.result;

import java.util.Collections;

/**
 * 统一返回包装类
 *
 * @param <T>
 * @author zhoup
 */
public class ResultS<T> {
    /**
     * 通用状态枚举
     */
    public enum ResultStatus {
        OK("success", 200),
        FAIL("failed", -1);
        private String message;
        private int resultCode;

        ResultStatus(String message, int resultCode) {
            this.message = message;
            this.resultCode = resultCode;
        }

        public String getMessage() {
            return message;
        }

        public int getResultCode() {
            return resultCode;
        }
    }

    /**
     * 返回的信息
     */
    private String msg;
    /**
     * 执行的状态
     */
    private Integer code;
    /**
     * 返回的数据字典
     */
    private T data;

    public ResultS(String msg, Integer code, T dataValue) {
        this.msg = msg;
        this.code = code;
        if (dataValue == null) data = (T) Collections.emptyList();
        this.data = dataValue;
    }

    public ResultS(String msg, Integer code) {
        this.msg = msg;
        this.code = code;
        data = (T) Collections.emptyList();
    }

    public ResultS(ResultStatus resultStatus, T data) {
        this.msg = resultStatus.getMessage();
        this.code = resultStatus.getResultCode();
        this.data = data;
    }

    public static <T> ResultS<T> success() {
        return new ResultS<T>(ResultStatus.OK, (T) Collections.EMPTY_LIST);
    }

    public static <T> ResultS<T> success(T data) {
        return new ResultS<T>(ResultStatus.OK, data);
    }

    public static <T> ResultS<T> success(String msg) {
        return new ResultS<T>(msg, ResultStatus.OK.getResultCode());
    }

    public static <T> ResultS<T> success(String msg, T data) {
        return new ResultS<T>(msg, ResultStatus.OK.getResultCode(), data);
    }

    public static <T> ResultS<T> success(Integer code, String msg, T data) {
        return new ResultS<T>(msg, code, data);
    }

    public static <T> ResultS<T> fail() {
        return new ResultS<T>(ResultStatus.FAIL, (T) Collections.EMPTY_LIST);
    }

    public static <T> ResultS<T> fail(T data) {
        return new ResultS<T>(ResultStatus.FAIL, data);
    }

    public static <T> ResultS<T> fail(String msg) {
        return new ResultS<T>(msg, ResultStatus.FAIL.getResultCode());
    }

    public static <T> ResultS<T> fail(String msg, T data) {
        return new ResultS<T>(msg, ResultStatus.FAIL.getResultCode(), data);
    }

    public static <T> ResultS<T> fail(Integer code, String msg, T data) {
        return new ResultS<T>(msg, code, data);
    }


}
