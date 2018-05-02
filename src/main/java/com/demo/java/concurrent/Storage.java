package com.demo.java.concurrent;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 仓库
 *
 * @author zhanghanlin
 */
public class Storage {

    BlockingDeque<Product> queues = new LinkedBlockingDeque<>();

    /**
     * 生产
     *
     * @param p ProductSync
     */
    public void push(Product p) {
        queues.push(p);
    }

    /**
     * 消费
     * 自动阻塞
     *
     * @return ProductSync
     * @throws InterruptedException
     */
    public Product pop() throws InterruptedException {
        return queues.take();
    }
}
