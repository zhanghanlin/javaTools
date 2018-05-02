package com.demo.java.design.factory;

/**
 * 工厂生产器
 *
 * @author zhanghanlin
 */
public class FactoryProducer {

    /**
     * 获取具体工厂
     *
     * @param producer
     * @return
     */
    public static AbstractFactory getFactory(FactoryEnum producer) {
        if (producer == null) {
            return null;
        }
        if (FactoryEnum.SHAPE.equals(producer)) {
            return new ShapeFactory();
        } else if (FactoryEnum.COLOR.equals(producer)) {
            return new ColorFactory();
        }
        return null;
    }
}
