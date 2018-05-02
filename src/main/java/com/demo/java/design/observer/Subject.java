package com.demo.java.design.observer;

/**
 * 主题接口
 *
 * @author zhanghanlin6
 */
public interface Subject {
    /**
     * 添加观察者
     *
     * @param o 观察者对象
     */
    void addObserver(Observer o);

    /**
     * 移除观察者
     *
     * @param o 观察者对象
     */
    void deleteObserver(Observer o);

    /**
     * 通知观察者更新
     */
    void notifyObservers();
}
