package com.java.tool.serial;

import java.util.UUID;

/**
 * 快速获取序号
 *
 * @author zhoup
 */
public class SerialUtil {
    private volatile SnowflakeIdWorker snowflakeIdWorker;

    private SerialUtil() {

    }

    /**
     * 获取实例
     *
     * @return
     */
    public final static SerialUtil getInstance() {
        return SerialInstance.INSTANCE;
    }

    private static class SerialInstance {
        private final static SerialUtil INSTANCE = new SerialUtil();

    }

    /**
     * 获取uuid
     * 返回剔除'-'符号的UUID
     *
     * @return
     */
    public String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 获取雪花算法id
     *
     * @return
     */
    public Long getSnowflakeId() {
        if (snowflakeIdWorker == null) {
            this.snowflakeIdWorker = new SnowflakeIdWorker(1, 1);
        }
        return snowflakeIdWorker.nextId();
    }
}
