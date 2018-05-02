package com.demo.java.concurrent.sync;

import java.util.ArrayList;
import java.util.List;

/**
 * 装产品的容器
 *
 * @author zhanghanlin
 */
public class ContainerSync<T> {

    private final int capacity;

    private final List<T> list;

    public ContainerSync(int capacity) {
        this.capacity = capacity;
        list = new ArrayList<>(capacity);
    }

    /**
     * 容器列表
     *
     * @return list
     */
    public List<T> getList() {
        return list;
    }

    /**
     * 最大容量
     *
     * @return capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * 添加产品
     *
     * @param t T
     * @return boolean
     */
    public synchronized boolean add(T t) {
        if (list.size() < capacity) {
            list.add(t);
            return true;
        }
        return false;
    }

    /**
     * 判断是否已经存满
     *
     * @return boolean
     */
    public synchronized boolean isFull() {
        return list.size() >= capacity;
    }

    /**
     * 判断是否为空
     *
     * @return boolean
     */
    public synchronized boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Get
     *
     * @return T
     */
    public synchronized T get() {
        if (list.size() > 0) {
            return list.remove(0);
        }
        return null;
    }

    /**
     * 获取长度
     *
     * @return int
     */
    public synchronized int getSize() {
        return list.size();
    }
}
