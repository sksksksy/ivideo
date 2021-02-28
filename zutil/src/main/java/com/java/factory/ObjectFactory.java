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
     * 默认支持的是函数编程接口设置代理
     *
     * @param targetClass
     * @param <T>
     * @return
     */
    public <T> T CglibCreateObject(Class<T> targetClass, MethodCallback methodCallback) {
        CglibProxy cglibProxy = new CglibProxy();
        cglibProxy.setMethodCallback(methodCallback);
        T proxy = cglibProxy.createProxy(targetClass);
        return proxy;
    }
}
