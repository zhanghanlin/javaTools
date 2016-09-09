package com.demo.java.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.demo.java.commons.Config.maxThreadNum;

/**
 * FixedThreadPool线程池
 * 创建固定大小(nThreads,大小不能超过int的最大值)的线程池
 */
public class FixedThreadPool extends ThreadPool {

    static ExecutorService executor = Executors.newFixedThreadPool(maxThreadNum);

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
        String task = "";//模拟任务
        for (int i = 0; i < 15; i++) {
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
