package com.demo.java.utils.crypto;

import com.demo.java.utils.string.BytesUtils;
import org.apache.commons.codec.binary.Base64;

/**
 * Base64加密工具类
 *
 * @author zhanghanlin
 */
public class Base64Utils {

    public static String encodeBase64(byte[] simple) {
        String cipher = BytesUtils.bytes2String(Base64.encodeBase64(simple));
        return cipher;
    }

    public static byte[] decoderBase64(String cipher) {
        byte[] simple = Base64.decodeBase64(cipher);
        return simple;
    }

    public static String encode(String simple) {
        return encodeBase64(simple.getBytes());
    }

    public static String encoder(byte[] simple) {
        return encodeBase64(simple);
    }

    public static String decode(String cipher) {
        return BytesUtils.bytes2String(decoderBase64(cipher));
    }

    public static byte[] decoder(String cipher) {
        return decoderBase64(cipher);
    }
}
