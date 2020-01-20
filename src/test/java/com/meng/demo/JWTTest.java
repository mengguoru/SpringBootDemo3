package com.meng.demo;

import com.meng.demo.utils.JWTUtil;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;

@SpringBootTest
public class JWTTest {

    @Autowired
    private JWTUtil jwtUtil;

    @Test
    void testJWT() throws NoSuchAlgorithmException {
//        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
//        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//        System.out.println(key.getFormat());

        Key key = jwtUtil.generateKey();

        String encodedKey = Base64.getEncoder().encodeToString(key.getEncoded());
        System.out.println(encodedKey);

//        Key key =
        JwtBuilder builder = Jwts.builder().setId("1")
                .setSubject("用户1")
                .setIssuedAt(new Date())
                .signWith(key);
        String token = builder.compact();
        System.out.println(token);

        System.out.println(Jwts.parser().setSigningKey("73zLf4oXXiCfG7MR96Usa+sKjlC7s8zCOw09H+nktFw=").parse(token).toString());
        System.out.println(Jwts.parser().setSigningKey(key).parse(token).toString());
        //System.out.println(Jwts.parser().setSigningKey("73zLf4oXXiCfG7MR96Usa+sKjlA7s8zCOw09H+nktFw=").parse(token).toString());;
    }

    @Test
    void testExpiration() throws NoSuchAlgorithmException, InterruptedException {
        Key key = jwtUtil.generateKey();
        //当前时间
        long now = System.currentTimeMillis();
        long expr = now + 60*1000;//一分钟后过期
        JwtBuilder builder = Jwts.builder().setId("1")
                .setSubject("用户1")
                .setIssuedAt(new Date())
                .setExpiration(new Date(expr))
                .signWith(key);
        String token = builder.compact();
        System.out.println(token);
        System.out.println(Jwts.parser().setSigningKey(key).parse(token).toString());
        //一分钟后执行
        Thread.currentThread().sleep(66*1000);
        System.out.println(Jwts.parser().setSigningKey(key).parse(token).toString());
    }

    @Test
    void testParserToken() throws NoSuchAlgorithmException {
        Key key = jwtUtil.generateKey();
        System.out.println(Jwts.parser().setSigningKey(key).parse("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoi55So5oi3MSIsImlhdCI6MTU3OTUzNDI4NCwiZXhwIjoxNTc5NTM0MzQ0fQ.BYvxJQREpvr9BkyUr0HLYIZYog9bRlkh5xt0W0eGnus").toString());
    }
}
