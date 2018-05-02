package com.demo.java.design.factory;

/**
 * 绿色接口
 *
 * @author zhanghanlin
 */
public class ColorGreen implements Color {

    @Override
    public void draw() {
        System.out.println("draw color Green");
    }
}
