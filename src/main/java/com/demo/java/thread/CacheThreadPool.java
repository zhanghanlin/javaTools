package com.demo.java.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池实例
 */
public class CacheThreadPool {
    private CacheThreadPool() {
    }

    /**
     * CPU核数
     */
    static final int maxThreadNum = Runtime.getRuntime().availableProcessors() + 1;

    static ExecutorService exec = Executors.newFixedThreadPool(maxThreadNum);

    static CacheThreadPool pool = new CacheThreadPool();

    static CacheThreadPool getInstance() {
        return pool;
    }

    public static void main(String[] args) {
        getInstance().run();
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            final int j = i;
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().toString() + ">>" + j);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        exec.shutdown();
    }
}
