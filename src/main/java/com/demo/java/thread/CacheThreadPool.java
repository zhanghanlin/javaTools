package com.demo.java.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CacheThreadPool线程池实例
 * 创建corePoolSize为0，最大线程数为整型的最大数
 * 线程keepAliveTime为1分钟，缓存任务的队列为SynchronousQueue的线程池
 */
public class CacheThreadPool extends ThreadPool {

    static ExecutorService executor = Executors.newCachedThreadPool();

    static CacheThreadPool cacheThreadPool = new CacheThreadPool();

    static CacheThreadPool getInstance() {
        return cacheThreadPool;
    }

    /**
     * 添加任务到线程池
     *
     * @param task 任务编号
     */
    private void runThreadPool(final String task) {
        executor.execute(() -> run(task));
    }

    public static void main(String[] args) {
        CacheThreadPool pool = CacheThreadPool.getInstance();
        String task = "";//模拟任务
        for (int i = 0; i < 15; i++) {
            pool.runThreadPool(i + task);
        }
        if (!executor.isShutdown()) {
            executor.shutdown();
        }
        System.out.println("executor is shutdown ->" + executor.isShutdown());
    }
}
