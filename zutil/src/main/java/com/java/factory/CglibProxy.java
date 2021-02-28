package com.java.factory;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

import java.util.Objects;

/**
 * cglib创建代理，基于继承
 */
public class CglibProxy {
    MethodInterceptor interceptor;

    public <T> T createProxy(Class<T> tClass) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(tClass);
        if (Objects.nonNull(interceptor)) {
            enhancer.setCallback(interceptor);
        }
        return (T) enhancer.create();
    }

    /**
     * 原始方式设置方法拦截
     *
     * @param interceptor
     */
    public void setMethodCallback(MethodInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    /**
     * 函数式方式设置方法拦截接口
     *
     * @param methodCallback
     */
    public void setMethodCallback(MethodCallback methodCallback) {
        this.interceptor = methodCallback;
    }
}
