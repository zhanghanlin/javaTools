package com.demo.java.utils.string;

public class BytesUtils {
    public static String bytes2String(byte[] bytesArray) {
        String result = "";
        for (Byte bts : bytesArray) {
            result += (char) bts.intValue();
        }
        return result;
    }
}
