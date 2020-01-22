package com.meng.demo.utils;

import org.junit.jupiter.api.Test;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

class RSAUtilTest {
    @Test
    void testRSA() throws Exception {
        var map = RSAUtil.generateKeyPair();
//        System.out.println(map.get("publicKey").toString());
//        System.out.println(map.get("privateKey").toString());
        RSAPublicKey key = (RSAPublicKey) map.get("publicKey");
        System.out.println(Base64.getEncoder().encodeToString(key.getEncoded()));;

        System.out.println("---分割线---");
        var map2 = RSAUtil.generateKeyPair2();
        //System.out.println(map2);
        for(var k:map2.entrySet()){
            System.out.println("键：" + k.getKey() + ", " + "值：" + k.getValue());
        }
        /*
        保存一组稍后用于测试
        键：privateKey, 值：MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANPo5KLSC1Opqg3tKCTp7ANXYV+TVwLR5d5xDfsE9jQCEUNnJxdF4gXQ4sJpYQYS7Tvgh/O/Qh2h1B3Nqtr+hLgc6ThHC2O/OxbhCYBNjv4Ft9v6i9xcBrJE3QWPGbYFT4w1Kc5IefePGHt8r8XeDo/ggSUL4Zdq6MCudbgrrrq7AgMBAAECgYBcNg23pDr1jvDG2poLZhcjZRSkKFNGkNu8Lentfz07aGLmt65U/D3cVgc2nfBwWjANTCkcse8tQcwxo9tjLDnHsWK0UINA86LuxOV5auOZYLEy0beeVXFrfM2AveTx1iv7MIshdzl1QjSxhHk7Kao+dkq0Yw9OwUjAm3v4fCG8AQJBAOxoWYjuSRCg7U26tTK2k54Dfq+kKsq/8tT3U96d8liVnYNULD1148uKO8yeX/sNFAGj/XlT8ohc8nQS9cl197sCQQDleMsuJdo4gS9yoU4gdKnHUfKDtMt3B8tSA3uwwW033j2BkopXVAm/sjG+zRaBhfG2h5mGooaXil7JwgDX3pkBAkAOjTXFqfUxYtZ4jF+O6ZIYjdqEcTR5GmuWFEtDTrGWJk91v/6hIdqNssqPg8ggqeNrXm7BwxsQrbf20Rvz9KD1AkB1Yn5lY/2mjAcul2900NkM2lG5qCGxCS6sDJYYjEoHm0tRXhogpB+8jTY/SYBqz48fdWxG4BsDqEfm47Q0uW0BAkEAyIFTe9QNPPJujOWI6FUrPh+3LP3HtzP0wP+HjSBybnSf2j6J+raa3JxHofys5FmsDEvpt1+mJhG4kVecWJhFpA==
键：publicKey, 值：MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDT6OSi0gtTqaoN7Sgk6ewDV2Ffk1cC0eXecQ37BPY0AhFDZycXReIF0OLCaWEGEu074Ifzv0IdodQdzara/oS4HOk4RwtjvzsW4QmATY7+Bbfb+ovcXAayRN0Fjxm2BU+MNSnOSHn3jxh7fK/F3g6P4IElC+GXaujArnW4K666uwIDAQAB
         */
        System.out.println("---分割线---");
        String pwd = "这是一条密码";
        String tmp = RSAUtil.encrypt(pwd, (RSAPublicKey) map.get("publicKey"));
        System.out.println(tmp);
        System.out.println(RSAUtil.decrypt(tmp, (RSAPrivateKey) map.get("privateKey")));
        System.out.println("---分割线---");
        tmp = RSAUtil.encrypt(pwd, map2.get("publicKey"));
        System.out.println(tmp);
        System.out.println(RSAUtil.decrypt(tmp, map2.get("privateKey")));
    }
}