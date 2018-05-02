package com.demo.java.design.observer;

/**
 * 观察者接口
 *
 * @author zhanghanlin
 */
public interface Observer {

    /**
     * 当被观察者调用notifyObservers()方法时,观察者的update()方法会被回调。
     *
     * @param message 消息
     */
    void update(String message);
}
