package com.demo.java.concurrent;

/**
 * 消费者
 */
public class Consumer implements Runnable {

    private String name;
    private Storage storage;

    public Consumer(String name, Storage storage) {
        this.name = name;
        this.storage = storage;
    }

    @Override
    public void run() {
        try {

            while (true) {
                System.out.println(name + " 准备消费");
                Product p = storage.pop();
                System.out.println(name + "已消费:" + p.toString());
                System.out.println("================");
                Thread.sleep(500L);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
