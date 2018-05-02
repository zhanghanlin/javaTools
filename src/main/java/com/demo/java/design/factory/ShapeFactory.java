package com.demo.java.design.factory;

/**
 * 模型工厂
 *
 * @author zhanghanlin
 */
public class ShapeFactory {

    /**
     * 获取模型
     *
     * @param shapeEnum ShapeEnum
     * @return Shape
     */
    public Shape getShape(ShapeEnum shapeEnum) {
        if (shapeEnum == null) {
            return null;
        }
        if (ShapeEnum.A.equals(shapeEnum)) {
            return new ShapeA();
        } else if (ShapeEnum.B.equals(shapeEnum)) {
            return new ShapeB();
        } else if (ShapeEnum.C.equals(shapeEnum)) {
            return new ShapeC();
        }
        return null;
    }
}
