package com.demo.java.concurrent.sync;

/**
 * 商品
 *
 * @author zhanghanlin
 */
public class ProductSync {
    private int id;

    public ProductSync(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ProductSync{" + "id=" + id + "}";
    }
}
