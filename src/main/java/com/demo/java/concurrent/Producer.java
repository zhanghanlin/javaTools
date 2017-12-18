package com.demo.java.concurrent;

import java.util.Random;

/**
 * 生产者
 *
 * @author zhanghanlin
 */
public class Producer implements Runnable {

    private String name;
    private Storage storage;

    public Producer(String name, Storage storage) {
        this.name = name;
        this.storage = storage;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Product p = new Product((new Random().nextInt() * 10000));
                System.out.println(name + "准备生产:" + p.toString());
                storage.push(p);
                System.out.println(name + "已生产:" + p.toString());
                System.out.println("==============");
                Thread.sleep(500L);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
