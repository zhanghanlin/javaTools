package com.demo.java.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.demo.java.commons.Config.MAX_THREAD_NUM;

/**
 * ScheduledThreadPool线程池
 * 创建corePoolSize大小的线程池
 * 支持定时及周期性任务执行
 *
 * @author zhanghanlin
 */
public class ScheduledThreadPool extends ThreadPool {

    static ScheduledExecutorService executor = Executors.newScheduledThreadPool(MAX_THREAD_NUM);

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
        executor.schedule(() -> run(task), 3, TimeUnit.SECONDS);
    }

    public static void main(String[] args) {
        ScheduledThreadPool pool = ScheduledThreadPool.getInstance();
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
