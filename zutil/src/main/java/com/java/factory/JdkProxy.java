package com.java.factory;

import com.java.exception.BaseRunException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.*;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Jdk创建代理，必须基于接口
 *
 * @param <T>
 */
@Slf4j
@AllArgsConstructor
public class JdkProxy<T> implements InvocationHandler {
    private Class<?> targetClazz;
    private Consumer<T> beforeConsumer;
    private Consumer<T> afterConsumer;
    private T beforeParam;
    private T afterParam;

    public JdkProxy() {
    }

    public JdkProxy(Class<?> targetClazz) {
        this.targetClazz = targetClazz;
    }

    public JdkProxy(T t, Class<?> targetClazz) {
        beforeParam = t;
        afterParam = t;
        this.targetClazz = targetClazz;
    }

    public JdkProxy(T t0, T t1, Class<?> targetClazz) {
        beforeParam = t0;
        afterParam = t1;
        this.targetClazz = targetClazz;
    }


    public void setBeforeConsumer(Consumer<T> consumer) {
        this.beforeConsumer = consumer;
    }

    public void setAfterConsumer(Consumer<T> consumer) {
        this.afterConsumer = consumer;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Objects.requireNonNull(targetClazz, "传入的class不能为空");
        Object obj = targetClazz.newInstance();
        Field[] fields = targetClazz.getDeclaredFields();
        Object result = null;
        try {
            if (Objects.nonNull(beforeConsumer)) {
                beforeConsumer.accept(beforeParam);
            }
            result = method.invoke(obj, args);
            if (Objects.nonNull(afterConsumer)) {
                afterConsumer.accept(afterParam);
            }
        } catch (InvocationTargetException e) {
            log.error("恭喜你触发代理函数调用内部错误", e);
            BaseRunException.throwException("恭喜你触发代理函数调用内部错误", e);
        }
        return result;
    }

    public Object getInstance() {
        System.out.println("here has been carry out." + targetClazz.getName());
        Object proxyObject = Proxy.newProxyInstance(this.getClass().getClassLoader(), targetClazz.getInterfaces(), this);
        return proxyObject;
    }

}
