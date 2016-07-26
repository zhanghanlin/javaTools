package com.demo.java.utils.crypto;


import com.demo.java.utils.string.BytesUtils;
import junit.framework.TestCase;
import org.junit.Assert;

public class Base64Example extends TestCase {

    public void testEncode() {
        String simple = "test";
        String enStr = Base64Utils.encode(simple);
        String deStr = Base64Utils.decode(enStr);
        Assert.assertTrue(deStr.equals(simple));
    }

    public void testEncoder() {
        String simple = "test";
        String enStr = Base64Utils.encoder(simple.getBytes());
        String deStr = BytesUtils.bytes2String(Base64Utils.decoder(enStr));
        Assert.assertTrue(deStr.equals(simple));
    }
}
