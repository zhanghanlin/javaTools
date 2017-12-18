package com.demo.java.concurrent.sync;

/**
 * 消费者
 *
 * @author zhanghanlin
 */
public class Consumer implements Runnable {

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

    public Consumer(Container<Product> container, Object producerMonitor, Object consumerMonitor) {
        this.container = container;
        this.producerMonitor = producerMonitor;
        this.consumerMonitor = consumerMonitor;
    }

    public void consume() {
        // 如果发现容器已经空了,消费者要停
        if (container.isEmpty()) {
            // 唤醒生产者
            synchronized (producerMonitor) {
                if (container.isEmpty()) {
                    producerMonitor.notify();
                }
            }
            // 消费者挂起
            synchronized (consumerMonitor) {
                if (container.isEmpty()) {
                    try {
                        System.out.println("Consumer wait...");
                        consumerMonitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            Product p = container.get();
            System.out.println("get Product : " + p.toString());
        }
    }

    @Override
    public void run() {
        while (true) {
            consume();
        }
    }
}
