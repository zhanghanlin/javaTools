package com.demo.java.utils.http;

import com.demo.java.BaseTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * HttpFactory Test Class
 *
 * @author zhanghanlin6
 */
public class HttpFactoryTest extends BaseTest {

    private final static Logger logger = LoggerFactory.getLogger(HttpFactoryTest.class);

    public static void main(String[] args) throws IOException {
        String url = "http://127.0.0.1";
        doGet(url);
        doPost(url);
    }

    static void doGet(String url) throws IOException {
        String result = HttpFactory.execute(url, null, HttpFactory.HttpMethod.GET);
        logger.info("doGet:{}", result);
    }

    static void doPost(String url) throws IOException {
        String result = HttpFactory.execute(url, null, HttpFactory.HttpMethod.POST);
        logger.info("doPost:{}", result);
    }
}
