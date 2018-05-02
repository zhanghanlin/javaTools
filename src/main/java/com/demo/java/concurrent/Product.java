package com.demo.java.concurrent;

/**
 * 商品
 *
 * @author zhanghanlin
 */
public class Product {
    private int id;

    public Product(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ProductSync{" + "id=" + id + "}";
    }
}
