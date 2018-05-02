package com.demo.java.design.factory;

/**
 * 抽象工厂
 *
 * @author zhanghanlin
 */
public abstract class AbstractFactory {
    /**
     * 获取模型
     *
     * @param shapeEnum ShapeEnum
     * @return Shape
     */
    public abstract Shape getShape(ShapeEnum shapeEnum);

    /**
     * 获取颜色
     *
     * @param colorEnum ColorEnum
     * @return Color
     */
    public abstract Color getColor(ColorEnum colorEnum);
}
