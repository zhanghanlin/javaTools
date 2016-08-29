package com.demo.java.concurrent;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 仓库
 */
public class Storage {

    BlockingDeque<Product> queues = new LinkedBlockingDeque<>();

    /**
     * 生产
     *
     * @param p
     */
    public void push(Product p) {
        queues.push(p);
    }

    /**
     * 消费
     *
     * @return
     * @throws InterruptedException
     */
    public Product pop() throws InterruptedException {
        // 消费产品，自动阻塞
        return queues.take();
    }
}
