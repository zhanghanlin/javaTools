package com.demo.java.design.factory;

/**
 * 颜色工厂
 *
 * @author zhanghanlin
 */
public class ColorFactory extends AbstractFactory {

    /**
     * 获取模型
     *
     * @param shapeEnum ShapeEnum
     * @return Shape
     */
    @Override
    public Shape getShape(ShapeEnum shapeEnum) {
        return null;
    }

    @Override
    public Color getColor(ColorEnum colorEnum) {
        if (colorEnum == null) {
            return null;
        }
        if (ColorEnum.RED.equals(colorEnum)) {
            return new ColorRed();
        } else if (ColorEnum.GREEN.equals(colorEnum)) {
            return new ColorGreen();
        } else if (ColorEnum.BLUE.equals(colorEnum)) {
            return new ColorBlue();
        }
        return null;
    }
}
