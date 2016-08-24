package com.demo.java.commons;

public interface Config {

    /**
     * 默认编码
     */
    String CHARSET = "UTF-8";

    /**
     * CPU核数
     */
    int maxThreadNum = Runtime.getRuntime().availableProcessors() + 1;
}
