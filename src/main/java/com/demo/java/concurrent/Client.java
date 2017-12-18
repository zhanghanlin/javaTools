package com.demo.java.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Client
 *
 * @author zhanghanlin
 */
public class Client {

    public static void main(String[] args) {
        Storage storage = new Storage();
        ExecutorService service = Executors.newCachedThreadPool();
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
