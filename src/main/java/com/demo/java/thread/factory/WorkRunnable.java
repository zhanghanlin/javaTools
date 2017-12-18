package com.demo.java.thread.factory;

/**
 * 定义一个测试目标
 *
 * @author zhanghanlin
 */
public class WorkRunnable implements Runnable {

    private int i;

    public WorkRunnable(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        System.out.println("complete a task : " + i);
    }
}
