package com.demo.java.commons;

/**
 * 默认公共配置
 *
 * @author zhanghanlin
 */
public interface Config {

    /**
     * 默认编码
     */
    String CHARSET = "UTF-8";

    /**
     * CPU核数
     */
    int MAX_THREAD_NUM = Runtime.getRuntime().availableProcessors() + 1;
}
