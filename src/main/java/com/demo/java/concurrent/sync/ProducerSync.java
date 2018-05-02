package com.demo.java.concurrent.sync;

import java.util.Random;

/**
 * 生产者
 *
 * @author zhanghanlin
 */
public class ProducerSync implements Runnable {

    /**
     * 简单的模拟，这里一个生产容器，设置成final类型的话不允许再次赋值
     */
    private final ContainerSync<ProductSync> containerSync;

    /**
     * 生产者监听器
     */
    private Object producerMonitor;

    /**
     * 消费者监听器
     */
    private Object consumerMonitor;

    public ProducerSync(ContainerSync<ProductSync> containerSync, Object producerMonitor, Object consumerMonitor) {
        this.containerSync = containerSync;
        this.producerMonitor = producerMonitor;
        this.consumerMonitor = consumerMonitor;
    }

    public void produce() {
        ProductSync p = new ProductSync(new Random().nextInt());
        // 如果发现容器已经满了,生产者要停
        if (containerSync.isFull()) {
            // 唤醒消费者
            synchronized (consumerMonitor) {
                if (containerSync.isFull()) {
                    consumerMonitor.notify();
                }
            }
            // 生产者挂起
            synchronized (producerMonitor) {
                if (containerSync.isFull()) {
                    try {
                        System.out.println("ProducerSync wait...");
                        producerMonitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            boolean result = containerSync.add(p);
            System.out.println("add ProductSync : " + result);
        }
    }

    @Override
    public void run() {
        while (true) {
            produce();
        }
    }
}
