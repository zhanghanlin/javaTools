package com.demo.java.utils.crypto;

public class DigestExample {

    public static void main(String[] args) {
        base64();
        hex();
    }

    public static void base64() {
        String simple = "test";
        System.out.println(DigestUtils.md5B64(simple));
        System.out.println(DigestUtils.shaB64(simple));
        System.out.println(DigestUtils.sha1B64(simple));
        System.out.println(DigestUtils.sha256B64(simple));
        System.out.println(DigestUtils.sha384B64(simple));
        System.out.println(DigestUtils.sha512B64(simple));
    }

    public static void hex() {
        String simple = "test";
        System.out.println(DigestUtils.md5Hex(simple));
        System.out.println(DigestUtils.shaHex(simple));
        System.out.println(DigestUtils.sha1Hex(simple));
        System.out.println(DigestUtils.sha256Hex(simple));
        System.out.println(DigestUtils.sha384Hex(simple));
        System.out.println(DigestUtils.sha512Hex(simple));
    }
}
