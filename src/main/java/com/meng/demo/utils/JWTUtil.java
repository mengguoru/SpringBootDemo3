package com.meng.demo.utils;

import com.meng.demo.pojo.User;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;

public class JWTUtil {
    private final String secretKey = "meng_secretkey";

    public final Key SecretKey;

    public JWTUtil() throws NoSuchAlgorithmException {
        SecretKey = generateKey();
    }

    public Key generateKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
        //SecureRandom secureRandom = new SecureRandom();
        SecureRandom secureRandom = new SecureRandom(secretKey.getBytes());
        keyGenerator.init(secureRandom);
        Key key = keyGenerator.generateKey();
        return key;
    }

    public String generateToken(User user){
        //当前时间
        long now = System.currentTimeMillis();
        long expr = now + 3*24*60*60*1000;//三天后过期
        JwtBuilder builder = Jwts.builder().setId(user.getId() + "")
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(expr))
                .signWith(SecretKey);
        String token = builder.compact();
        return token;
    }
}
