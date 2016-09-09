package com.demo.java.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {

    public static void main(String[] args) {
        Storage storage = new Storage();
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 4; i++) {
            service.submit(new Producer(i + "", storage));
        }
        for (int i = 0; i < 2; i++) {
            service.submit(new Consumer(i + "", storage));
        }
    }
}
