package com.demo.java.concurrent.sync;

/**
 *
 */
public class Client {

    public static void main(String[] args) {
        Object producerMonitor = new Object();
        Object consumerMonitor = new Object();
        Container<Product> container = new Container<>(10);
        // 生产者启动
        for (int i = 0; i < 4; i++) {
            new Thread(new Producer(container, producerMonitor, consumerMonitor)).start();
        }
        // 消费者启动
        for (int i = 0; i < 2; i++) {
            new Thread(new Consumer(container, producerMonitor, consumerMonitor)).start();
        }
    }
}
