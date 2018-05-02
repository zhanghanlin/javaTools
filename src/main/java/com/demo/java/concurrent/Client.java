package com.demo.java.concurrent;

import com.demo.java.thread.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Client
 *
 * @author zhanghanlin
 */
public class Client {

    public static void main(String[] args) {
        Storage storage = new Storage();
        ExecutorService service = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS, new SynchronousQueue<>(), ThreadPool.nameFactory);
        int producerCount = 4;
        int consumerCount = 2;
        for (int i = 0; i < producerCount; i++) {
            service.submit(new Producer(i + "", storage));
        }
        for (int i = 0; i < consumerCount; i++) {
            service.submit(new Consumer(i + "", storage));
        }
    }
}
