package com.demo.java.singleton;

/**
 * 静态内部类
 * 利用了classLoader的机制来保证初始化instance时只有一个线程
 * 类被装载，instance不一定被初始化
 * 只有显示通过调用getInstance方法时，才会显示装载SingletonHolder类，从而实例化instance
 *
 * @author zhanghanlin
 */
public class SingletonC {

    private static class SingletonHolder {
        private static final SingletonC INSTANCE = new SingletonC();
    }

    private SingletonC() {
    }

    public static SingletonC getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
