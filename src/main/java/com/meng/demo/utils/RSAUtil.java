package com.meng.demo.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.*;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;


public class RSAUtil {
    private final static int INT_SIZE = 1024;

    private final static String PUBLIC_KEY = "publicKey";
    private final static String PRIVATE_KEY = "privateKey";

    public static Map<String, Object> generateKeyPair() throws Exception{
        KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
        keyGenerator.initialize(INT_SIZE);
        KeyPair keyPair = keyGenerator.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey)keyPair.getPrivate();
        var map = new HashMap<String, Object>();
        map.put(PUBLIC_KEY, publicKey);
        map.put(PRIVATE_KEY, privateKey);
        return map;
    }

    public static Map<String, String> generateKeyPair2() throws Exception{
        KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
        keyGenerator.initialize(INT_SIZE);
        KeyPair keyPair = keyGenerator.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey)keyPair.getPrivate();
        var map = new HashMap<String, String>();
        map.put(PUBLIC_KEY, Base64.getEncoder().encodeToString(publicKey.getEncoded()));
        map.put(PRIVATE_KEY, Base64.getEncoder().encodeToString(privateKey.getEncoded()));
        return map;
    }

    /*
    从字符串获取publicKey
     */
    public static RSAPublicKey loadPublicKey(String str) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] bytes = Base64.getDecoder().decode(str);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(bytes);
        return (RSAPublicKey)keyFactory.generatePublic(keySpec);
    }

    /*
    从字符串获取privateKey
     */
    public static RSAPrivateKey loadPrivateKey(String str) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] bytes = Base64.getDecoder().decode(str);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpecPKCS8 = new PKCS8EncodedKeySpec(bytes);
        return (RSAPrivateKey)keyFactory.generatePrivate(keySpecPKCS8);
    }

    /*
    使用公钥加密字符串
     */
    public static String encrypt(String data, RSAPublicKey publicKey) throws Exception{
        byte[] bytes = data.getBytes();
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] output = cipher.doFinal(bytes);
        return Base64.getEncoder().encodeToString(output);
    }

    public static String encrypt(String data, String publicKey) throws Exception{
        return encrypt(data, loadPublicKey(publicKey));
    }

    /*
    使用私钥解密字符串
     */
    public static String decrypt(String data, RSAPrivateKey privateKey) throws Exception{
        byte[] bytes = Base64.getDecoder().decode(data);
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(cipher.doFinal(bytes));
    }

    public static String decrypt(String data, String privateKey) throws Exception {
        return decrypt(data, loadPrivateKey(privateKey));
    }
}
