package com.demo.java.design.singleton;

/**
 * 双重校验锁
 *
 * @author zhanghanlin
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
