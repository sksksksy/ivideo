package com.java.thread;

import com.java.exception.BaseRunException;

import java.util.Objects;
import java.util.concurrent.*;

/**
 * 异步帮组类
 *
 * @author zhoup
 */
public class AsyncHelper {
    private AsyncHelper() {
    }

    /**
     * 初始化线程池
     */
    private ThreadPoolExecutor pool = new ThreadPoolExecutor(1, 3, 15,
            TimeUnit.SECONDS, new LinkedBlockingDeque<>(), new ThreadPoolExecutor.DiscardPolicy());
    /**
     * 声明future缓存
     */
    private ConcurrentHashMap<String, Future<?>> futures = new ConcurrentHashMap<>();

    /**
     * 懒加载，单例模式
     * @return
     */
    public final static AsyncHelper getHelper() {
        return factory.instance;
    }

    /**
     * 实现懒加载，线程安全
     */
    private static class factory {
        private static AsyncHelper instance = new AsyncHelper();
    }

    /**
     * 提交没有返回值的任务
     *
     * @param runnable
     */
    public void pushForNoReturn(Runnable runnable) {
        pool.execute(runnable);
    }

    /**
     * 提交有返回值的任务
     *
     * @param callable
     * @param <V>
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public <V> ConcurrentHashMap pushForReturn(Callable<V> callable, String name) {
        Future<V> submit = pool.submit(callable);
        futures.put(name, submit);
        return futures;
    }

    /**
     * 根据name获取future
     *
     * @param name
     * @return
     */
    public Future<?> getFuture(String name) {
        Future<?> future = futures.get(name);
        BaseRunException.check(Objects.isNull(future), name + ":对应的future为空！");
        return future;
    }
}