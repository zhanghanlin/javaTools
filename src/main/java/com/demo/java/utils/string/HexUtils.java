package com.demo.java.utils.string;

import java.io.UnsupportedEncodingException;

/**
 * Hex工具
 *
 * @author zhanghanlin
 */
public class HexUtils {

    static final byte[] HEX_CHAR_TABLE = {
            (byte) '0', (byte) '1', (byte) '2', (byte) '3',
            (byte) '4', (byte) '5', (byte) '6', (byte) '7',
            (byte) '8', (byte) '9', (byte) 'a', (byte) 'b',
            (byte) 'c', (byte) 'd', (byte) 'e', (byte) 'f'};

    public static String bytes2HexString(byte[] raw) {
        byte[] hex = new byte[2 * raw.length];
        int index = 0;
        for (byte b : raw) {
            int v = b & 0xFF;
            hex[index++] = HEX_CHAR_TABLE[v >>> 4];
            hex[index++] = HEX_CHAR_TABLE[v & 0xF];
        }
        String s = "";
        try {
            s = new String(hex, "ASCII");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return s;
    }
}
