package com.demo.java.concurrent;

import com.demo.java.BaseTest;
import com.demo.java.concurrent.sync.ConsumerSync;
import com.demo.java.concurrent.sync.ContainerSync;
import com.demo.java.concurrent.sync.ProducerSync;
import com.demo.java.concurrent.sync.ProductSync;
import com.demo.java.thread.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 生产者-消费者模式测试
 *
 * @author zhanghanlin
 */
public class ConsumerProducerTest extends BaseTest {

    static int type = 1;

    public static void main(String[] args) {
        if (type == 1) {
            blockingDequeType();
        } else {
            synchronizedType();
        }
    }

    /**
     * BlockingDeque方式
     */
    public static void blockingDequeType() {
        ExecutorService service = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS, new SynchronousQueue<>(), ThreadPool.nameFactory);
        Storage storage = new Storage();
        int producerCount = 4;
        int consumerCount = 2;
        for (int i = 0; i < producerCount; i++) {
            service.submit(new Producer(i + "", storage));
        }
        for (int i = 0; i < consumerCount; i++) {
            service.submit(new Consumer(i + "", storage));
        }
    }

    /**
     * synchronized方式
     * 线程资源必须通过线程池提供，不允许在应用中自行显式创建线程
     * 使用线程池的好处是减少在创建和销毁线程上所花的时间以及系统资源的开销，解决资源不足的问题
     * 如果不使用线程池，有可能造成系统创建大量同类线程而导致消耗完内存或者“过度切换”的问题。
     */
    public static void synchronizedType() {
        Object producerMonitor = new Object();
        Object consumerMonitor = new Object();
        ContainerSync<ProductSync> containerSync = new ContainerSync<>(10);
        int producerCount = 4;
        int consumerCount = 2;
        // 生产者启动
        for (int i = 0; i < producerCount; i++) {
            new Thread(new ProducerSync(containerSync, producerMonitor, consumerMonitor)).start();
        }
        // 消费者启动
        for (int i = 0; i < consumerCount; i++) {
            new Thread(new ConsumerSync(containerSync, producerMonitor, consumerMonitor)).start();
        }
    }
}
