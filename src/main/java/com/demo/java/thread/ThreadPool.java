package com.demo.java.thread;

/**
 * 线程池不允许使用Executors去创建，而是通过ThreadPoolExecutor的方式
 * 这样的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险
 * 说明：Executors各个方法的弊端：
 * newFixedThreadPool和newSingleThreadExecutor:主要问题是堆积的请求处理队列可能会耗费非常大的内存，甚至OOM。
 * newCachedThreadPool和newScheduledThreadPool:主要问题是线程数最大数是Integer.MAX_VALUE，可能会创建数量非常多的线程，甚至OOM。
 *
 * @author zhanghanlin
 */
public class ThreadPool {

    static final int DEFAULT_RUN_COUNT = 15;

    public void run(String task) {
        System.out.println("run task :" + task);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
