package com.demo.java.thread.factory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 构建线程封装类WorkThread
 * 该类的功能主要是为了能够更好的管理线程而创建的
 */
public class WorkThread extends Thread {

    private Runnable target;
    private AtomicInteger counter;

    public WorkThread(Runnable target, AtomicInteger counter) {
        this.target = target;
        this.counter = counter;
    }

    @Override
    public void run() {
        try {
            target.run();
        } finally {
            //getAndDecrement后置递减
            System.out.println("terminate No." + counter.getAndDecrement() + " Threads");
        }
    }
}
