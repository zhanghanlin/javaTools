package com.demo.java.design.factory;

/**
 * 自定义模型
 *
 * @author zhanghanlin
 */
public class ShapeC implements Shape {

    @Override
    public void create() {
        System.out.println("create demo shape C");
    }
}
