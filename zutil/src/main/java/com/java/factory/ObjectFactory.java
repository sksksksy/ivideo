package com.java.factory;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

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
    /***
     * =============================下面是优化的时候可能需要用的=========================
     */
    /**
     * 软引用创建
     * 指的是那些有用但是不是必须要的对象。系统在发生内存溢出前会对这类引用的对象进行回收
     *
     * @param referent
     * @param <T>
     * @return
     */
    public <T> T softCreate(T referent) {
        return new SoftReference<T>(referent).get();
    }

    /**
     * 弱引用创建
     * 他的强度比软引用更低一点，弱引用的对象下一次GC的时候一定会被回收，而不管内存是否足够
     *
     * @param referent
     * @param <T>
     * @return
     */
    public <T> T weakCreate(T referent) {
        return new SoftReference<T>(referent).get();
    }

    /**
     * 虚引用创建
     * 是最弱的引用关系，可以用PhantomReference来描述，
     * 他必须和ReferenceQueue一起使用，同样的当发生GC的时候，虚引用也会被回收。可以用虚引用来管理堆外内存
     *
     * @param referent
     * @param <T>
     * @return
     */
    public <T> T phantomCreate(T referent) {
        return new PhantomReference<T>(referent, new ReferenceQueue<>()).get();
    }
}
