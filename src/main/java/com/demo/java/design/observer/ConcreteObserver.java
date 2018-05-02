package com.demo.java.design.observer;

/**
 * 具体观察者
 *
 * @author zhanghanlin6
 */
public class ConcreteObserver implements Observer {

    /**
     * 观察者名称
     */
    String name;
    /**
     * 观察主题
     */
    Subject subject;

    public ConcreteObserver(String name, Subject subject) {
        this.name = name;
        this.subject = subject;
        subject.addObserver(this);
    }

    @Override
    public void update(String message) {
        System.out.println(name + "-接收:" + message);
    }
}
