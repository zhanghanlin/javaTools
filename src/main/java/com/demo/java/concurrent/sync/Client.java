package com.demo.java.concurrent.sync;

/**
 * 同步示例
 * <p>
 * 线程资源必须通过线程池提供，不允许在应用中自行显式创建线程
 * 使用线程池的好处是减少在创建和销毁线程上所花的时间以及系统资源的开销，解决资源不足的问题。如果不使用线程池，有可能造成系统创建大量同类线程而导致消耗完内存或者“过度切换”的问题。
 *
 * @author zhanghanlin
 */
public class Client {

    public static void main(String[] args) {
        Object producerMonitor = new Object();
        Object consumerMonitor = new Object();
        Container<Product> container = new Container<>(10);
        int producerCount = 4;
        int consumerCount = 2;
        // 生产者启动
        for (int i = 0; i < producerCount; i++) {
            new Thread(new Producer(container, producerMonitor, consumerMonitor)).start();
        }
        // 消费者启动
        for (int i = 0; i < consumerCount; i++) {
            new Thread(new Consumer(container, producerMonitor, consumerMonitor)).start();
        }
    }
}
