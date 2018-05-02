package com.demo.java.design;

import com.demo.java.BaseTest;
import com.demo.java.design.factory.*;

/**
 * 抽象工厂模式测试
 *
 * @author zhanghanlin
 */
public class FactoryTest extends BaseTest {

    public static void main(String[] args) {
        AbstractFactory shapeFactory = FactoryProducer.getFactory(FactoryEnum.SHAPE);
        AbstractFactory colorFactory = FactoryProducer.getFactory(FactoryEnum.COLOR);
        shapeFactory.getShape(ShapeEnum.A).create();
        shapeFactory.getShape(ShapeEnum.B).create();
        shapeFactory.getShape(ShapeEnum.C).create();

        colorFactory.getColor(ColorEnum.RED).draw();
        colorFactory.getColor(ColorEnum.GREEN).draw();
        colorFactory.getColor(ColorEnum.BLUE).draw();
    }
}
