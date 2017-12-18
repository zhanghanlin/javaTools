package com.demo.java.concurrent.sync;

import java.util.ArrayList;
import java.util.List;

/**
 * 装产品的容器
 *
 * @author zhanghanlin
 */
public class Container<T> {

    private final int capacity;

    private final List<T> list;

    public Container(int capacity) {
        this.capacity = capacity;
        list = new ArrayList<>(capacity);
    }

    public List<T> getList() {
        return list;
    }

    public int getCapacity() {
        return capacity;
    }

    /**
     * 添加产品
     *
     * @param t
     * @return
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
     * @return
     */
    public synchronized boolean isFull() {
        return list.size() >= capacity;
    }

    /**
     * 判断是否为空
     *
     * @return
     */
    public synchronized boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Get
     *
     * @return
     */
    public synchronized T get() {
        if (list.size() > 0) {
            return list.remove(0);
        }
        return null;
    }

    public synchronized int getSize() {
        return list.size();
    }
}
