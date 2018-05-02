package com.demo.java.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.demo.java.commons.Config.MAX_THREAD_NUM;

/**
 * FixedThreadPool线程池
 * 创建固定大小(nThreads,大小不能超过int的最大值)的线程池
 *
 * @author zhanghanlin
 */
public class FixedThreadPool extends ThreadPool {

    static ExecutorService executor = new ThreadPoolExecutor(MAX_THREAD_NUM, MAX_THREAD_NUM,
            0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), nameFactory);

    private static FixedThreadPool fixedThreadPool = new FixedThreadPool();

    public static FixedThreadPool getInstance() {
        return fixedThreadPool;
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
        FixedThreadPool pool = FixedThreadPool.getInstance();
        String task = "";
        for (int i = 0; i < DEFAULT_RUN_COUNT; i++) {
            pool.runThreadPool(i + task);
        }
        try {
            executor.awaitTermination(5L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!executor.isShutdown()) {
            executor.shutdown();
        }
        System.out.println("executor is shutdown ->" + executor.isShutdown());
    }
}
