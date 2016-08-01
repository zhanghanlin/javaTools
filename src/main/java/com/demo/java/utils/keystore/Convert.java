package com.demo.java.utils.keystore;

import org.apache.commons.lang.StringUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.util.Enumeration;

/**
 * pfx与keystore证书互转
 */
public class Convert {
    public static final String PKCS12 = "PKCS12";
    public static final String JKS = "JKS";
    public static final String PFX_KEYSTORE_FILE = "/usr/local/test.pfx";
    public static final String KEYSTORE_PASSWORD = "123456";
    public static final String JKS_KEYSTORE_FILE = "/usr/local/test.keystore";

    public static void execute(String from, String fromFile, String to, String toFile) {
        try {
            KeyStore inputKeyStore = KeyStore.getInstance(from);
            FileInputStream fis = new FileInputStream(fromFile);
            char[] nPassword;
            if (StringUtils.isBlank(KEYSTORE_PASSWORD)) {
                nPassword = null;
            } else {
                nPassword = KEYSTORE_PASSWORD.toCharArray();
            }
            inputKeyStore.load(fis, nPassword);
            fis.close();
            KeyStore outputKeyStore = KeyStore.getInstance(to);
            outputKeyStore.load(null, KEYSTORE_PASSWORD.toCharArray());
            Enumeration<String> enums = inputKeyStore.aliases();
            while (enums.hasMoreElements()) {
                String keyAlias = enums.nextElement();
                System.out.println("alias=[" + keyAlias + "]");
                if (inputKeyStore.isKeyEntry(keyAlias)) {
                    Key key = inputKeyStore.getKey(keyAlias, nPassword);
                    Certificate[] certChain = inputKeyStore.getCertificateChain(keyAlias);
                    outputKeyStore.setKeyEntry(keyAlias, key, KEYSTORE_PASSWORD.toCharArray(), certChain);
                }
            }
            FileOutputStream out = new FileOutputStream(toFile);
            outputKeyStore.store(out, nPassword);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        execute(PKCS12, PFX_KEYSTORE_FILE, JKS, JKS_KEYSTORE_FILE);
    }
}
