package com.demo.java.thread;

import java.util.concurrent.*;

/**
 * CacheThreadPool线程池实例
 * 创建corePoolSize为0，最大线程数为整型的最大数
 * 线程keepAliveTime为1分钟，缓存任务的队列为SynchronousQueue的线程池
 *
 * @author zhanghanlin
 */
public class CacheThreadPool extends ThreadPool {

    static ExecutorService executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
            60L, TimeUnit.SECONDS, new SynchronousQueue<>(), nameFactory);

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
        String task = "";
        for (int i = 0; i < DEFAULT_RUN_COUNT; i++) {
            pool.runThreadPool(i + task);
        }
        if (!executor.isShutdown()) {
            executor.shutdown();
        }
        System.out.println("executor is shutdown ->" + executor.isShutdown());
    }
}
