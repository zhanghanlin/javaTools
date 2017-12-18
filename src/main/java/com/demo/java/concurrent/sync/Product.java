package com.demo.java.concurrent.sync;

/**
 * 商品
 */
public class Product {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                '}';
    }
}