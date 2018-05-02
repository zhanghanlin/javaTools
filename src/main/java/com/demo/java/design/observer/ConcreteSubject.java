package com.demo.java.design.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 定义主题
 *
 * @author zhanghanlin
 */
public class ConcreteSubject implements Subject {

    /**
     * 这个List集合的泛型参数为Observer接口
     */
    List<Observer> list;
    /**
     * 消息信息
     */
    String message;

    public ConcreteSubject() {
        list = new ArrayList<>();
    }

    @Override
    public void addObserver(Observer o) {
        if (!list.contains(o)) {
            list.add(o);
        }
    }

    @Override
    public void deleteObserver(Observer o) {
        if (list.contains(o)) {
            list.remove(o);
        }

    }

    @Override
    public void notifyObservers() {
        for (int i = 0; i < list.size(); i++) {
            Observer observer = list.get(i);
            observer.update(message);
        }
    }

    public void pushMessage(String message) {
        this.message = message;
        System.out.println("发布:" + message);
        notifyObservers();
    }
}
