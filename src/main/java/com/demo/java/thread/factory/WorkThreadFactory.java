package com.demo.java.thread.factory;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 实现线程工厂的方法
 */
public class WorkThreadFactory implements ThreadFactory {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public Thread newThread(Runnable r) {
        //incrementAndGet前置递增
        System.out.println("create No." + atomicInteger.incrementAndGet() + " Threads");
        //通过计数器，可以更好的管理线程
        return new WorkThread(r, atomicInteger);
    }
}
