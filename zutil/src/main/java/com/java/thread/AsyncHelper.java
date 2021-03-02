package com.java.thread;

import com.java.exception.BaseRunException;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.concurrent.*;

/**
 * 异步帮组类
 *
 * @author zhoup
 */
@Slf4j
public class AsyncHelper {

    private AsyncHelper() {
        //允许设置核心线程超时后的线程池超时自毁
        pool.allowCoreThreadTimeOut(true);
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
     *
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
     * 防止重复提交
     *
     * @param runnable
     * @param key
     */
    public void submit(Runnable runnable, String key) {
        if (futures.containsKey(key)) {
            log.info("重复key:" + key);
            Future future = futures.get(key);
            System.out.println(future.isDone());
            if (future.isDone()) {
                futures.remove(key);
            } else {
                log.info("上一key:" + key + "还未执行完成，不会提交该任务");
                return;
            }
        }
        Future<?> future = pool.submit(runnable);
        futures.putIfAbsent(key, future);
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