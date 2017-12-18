package com.demo.java.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * SingleThreadPool线程池实例
 * 创建大小为1的固定线程池
 *
 * @author zhanghanlin
 */
public class SingleThreadPool extends ThreadPool {

    static ExecutorService executor = Executors.newSingleThreadExecutor();

    static SingleThreadPool singleThreadPool = new SingleThreadPool();

    static SingleThreadPool getInstance() {
        return singleThreadPool;
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
        SingleThreadPool pool = SingleThreadPool.getInstance();
        //模拟任务
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
