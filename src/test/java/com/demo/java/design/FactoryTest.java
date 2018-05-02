package com.demo.java.design;

import com.demo.java.BaseTest;
import com.demo.java.design.factory.Shape;
import com.demo.java.design.factory.ShapeEnum;
import com.demo.java.design.factory.ShapeFactory;

/**
 * 工厂模式测试
 *
 * @author zhanghanlin
 */
public class FactoryTest extends BaseTest {

    public static void main(String[] args) {
        ShapeFactory factory = new ShapeFactory();
        Shape a = factory.getShape(ShapeEnum.A);
        a.create();
        Shape b = factory.getShape(ShapeEnum.B);
        a.create();
        Shape c = factory.getShape(ShapeEnum.C);
        a.create();
    }
}
