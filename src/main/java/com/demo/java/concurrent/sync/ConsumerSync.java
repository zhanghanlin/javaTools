package com.demo.java.concurrent.sync;

/**
 * 消费者
 *
 * @author zhanghanlin
 */
public class ConsumerSync implements Runnable {

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

    public ConsumerSync(ContainerSync<ProductSync> containerSync, Object producerMonitor, Object consumerMonitor) {
        this.containerSync = containerSync;
        this.producerMonitor = producerMonitor;
        this.consumerMonitor = consumerMonitor;
    }

    public void consume() {
        // 如果发现容器已经空了,消费者要停
        if (containerSync.isEmpty()) {
            // 唤醒生产者
            synchronized (producerMonitor) {
                if (containerSync.isEmpty()) {
                    producerMonitor.notify();
                }
            }
            // 消费者挂起
            synchronized (consumerMonitor) {
                if (containerSync.isEmpty()) {
                    try {
                        System.out.println("ConsumerSync wait...");
                        consumerMonitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            ProductSync p = containerSync.get();
            System.out.println("get ProductSync : " + p.toString());
        }
    }

    @Override
    public void run() {
        while (true) {
            consume();
        }
    }
}
