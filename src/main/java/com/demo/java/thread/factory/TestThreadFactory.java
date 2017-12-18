package com.demo.java.thread.factory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.demo.java.commons.Config.MAX_THREAD_NUM;

/**
 * 测试线程工厂
 */
public class TestThreadFactory {
    public static void main(String[] args) {
        //创建线程（并发）池，自动伸缩(自动条件线程池大小)
//        ExecutorService executor = Executors.newCachedThreadPool(new WorkThreadFactory());
        ExecutorService executor = Executors.newFixedThreadPool(MAX_THREAD_NUM, new WorkThreadFactory());
//        ExecutorService executor = Executors.newSingleThreadExecutor(new WorkThreadFactory());
//        ExecutorService executor = Executors.newScheduledThreadPool(MAX_THREAD_NUM, new WorkThreadFactory());
        //同时并发10个工作线程
        for (int i = 0; i < 10; i++) {
            executor.execute(new WorkRunnable(i));
        }
        //指示当所有线程执行完毕后关闭线程池和工作线程，如果不调用此方法，jvm不会自动关闭
        executor.shutdown();
        try {
            //等待线程执行完毕，不能超过2*60秒，配合shutDown
            executor.awaitTermination(2 * 60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
