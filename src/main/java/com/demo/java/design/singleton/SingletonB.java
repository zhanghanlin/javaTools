package com.demo.java.design.singleton;

/**
 * 懒汉
 * 线程不安全
 * 这种写法lazy loading很明显，但是致命的是在多线程不能正常工作
 *
 * @author zhanghanlin
 */
public class SingletonB {

    private static SingletonB instance;

    private SingletonB() {
    }

    public static SingletonB getInstance() {
        if (instance == null) {
            instance = new SingletonB();
        }
        return instance;
    }
}
