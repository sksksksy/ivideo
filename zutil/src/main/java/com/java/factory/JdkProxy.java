package com.java.factory;

import com.java.exception.BaseRunException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.*;
import java.util.Objects;

@Slf4j
@AllArgsConstructor
public class JdkProxy implements InvocationHandler {
    private Class<?> targetClazz;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Objects.requireNonNull(targetClazz, "传入的class不能为空");
        Object obj = targetClazz.newInstance();
        Field[] fields = targetClazz.getDeclaredFields();
        Object result = null;
        try {
            result = method.invoke(obj, args);
        } catch (InvocationTargetException e) {
            log.error("恭喜你触发代理函数调用内部错误", e);
            BaseRunException.throwException("恭喜你触发代理函数调用内部错误", e);
        }
        return result;
    }

    public Object getInstance() {
        System.out.println("here has been carry out." + targetClazz.getCanonicalName());
        Object proxyObject = Proxy.newProxyInstance(this.getClass().getClassLoader(), targetClazz.getInterfaces(), this);
        return proxyObject;
    }

}
