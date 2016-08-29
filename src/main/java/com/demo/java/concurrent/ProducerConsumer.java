package com.demo.java.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProducerConsumer {

    public static void main(String[] args) {
        Storage storage = new Storage();
        ExecutorService service = Executors.newCachedThreadPool();
        Producer p1 = new Producer("A", storage);
        Producer p2 = new Producer("B", storage);
        Producer p3 = new Producer("C", storage);

        Consumer c1 = new Consumer("XF1", storage);
        Consumer c2 = new Consumer("XF2", storage);
        Consumer c3 = new Consumer("XF3", storage);

        service.submit(p1);
        service.submit(p2);
        service.submit(p3);
        service.submit(c1);
        service.submit(c2);
        service.submit(c3);
    }
}
