package com.java.factory;

/**
 * 生产代理类
 */
public class ObjectFactory {
    /**
     * jdk代理
     *
     * @param targetClass
     * @param <T>
     * @return
     */
    public <T> T JdkCreateObject(Class<T> targetClass) {
        JdkProxy jdkProxy = new JdkProxy(targetClass);
        return (T) jdkProxy.getInstance();
    }

    /**
     * cglib代理
     *
     * @param targetClass
     * @param <T>
     * @return
     */
    public <T> T CglibCreateObject(Class<T> targetClass) {
        JdkProxy jdkProxy = new JdkProxy(targetClass);
        return (T) jdkProxy.getInstance();
    }
}
