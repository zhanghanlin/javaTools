package com.demo.java.design.factory;

/**
 * 红色接口
 *
 * @author zhanghanlin
 */
public class ColorRed implements Color {

    @Override
    public void draw() {
        System.out.println("draw color Red");
    }
}
