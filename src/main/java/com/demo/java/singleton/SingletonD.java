package com.demo.java.singleton;

/**
 * 双重校验锁
 */
public class SingletonD {

    private volatile static SingletonD instance;

    private SingletonD() {
    }

    public static SingletonD getInstance() {
        if (instance == null) {
            synchronized (SingletonD.class) {
                if (instance == null) {
                    instance = new SingletonD();
                }
            }
        }
        return instance;
    }
}
