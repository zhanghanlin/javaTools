package com.demo.java.thread;

public class ThreadPool {

    public void run(String task) {
        System.out.println("run task :" + task);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
