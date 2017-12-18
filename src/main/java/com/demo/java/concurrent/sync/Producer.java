package com.demo.java.concurrent.sync;

/**
 * 生产者
 *
 * @author zhanghanlin
 */
public class Producer implements Runnable {

    /**
     * 简单的模拟，这里一个生产容器，设置成final类型的话不允许再次赋值
     */
    private final Container<Product> container;

    /**
     * 生产者监听器
     */
    private Object producerMonitor;

    /**
     * 消费者监听器
     */
    private Object consumerMonitor;

    public Producer(Container<Product> container, Object producerMonitor, Object consumerMonitor) {
        this.container = container;
        this.producerMonitor = producerMonitor;
        this.consumerMonitor = consumerMonitor;
    }

    public void produce() {
        Product p = new Product((int) Math.random() * 100);
        // 如果发现容器已经满了,生产者要停
        if (container.isFull()) {
            // 唤醒消费者
            synchronized (consumerMonitor) {
                if (container.isFull()) {
                    consumerMonitor.notify();
                }
            }
            // 生产者挂起
            synchronized (producerMonitor) {
                if (container.isFull()) {
                    try {
                        System.out.println("Producer wait...");
                        producerMonitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            boolean result = container.add(p);
            System.out.println("add Product : " + result);
        }
    }

    @Override
    public void run() {
        while (true) {
            produce();
        }
    }
}
