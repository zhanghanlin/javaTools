package com.demo.java.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.demo.java.commons.Config.maxThreadNum;

/**
 * ScheduledThreadPool线程池
 */
public class ScheduledThreadPool extends ThreadPool {

    static ExecutorService executor = Executors.newScheduledThreadPool(maxThreadNum);

    private static ScheduledThreadPool scheduledThreadPool = new ScheduledThreadPool();

    public static ScheduledThreadPool getInstance() {
        return scheduledThreadPool;
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
        ScheduledThreadPool pool = ScheduledThreadPool.getInstance();
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
