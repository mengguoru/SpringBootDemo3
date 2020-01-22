package com.meng.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class EncryTest {
    @Test
    void bcryptTest(){
        var bcrypt = new BCryptPasswordEncoder();
        var s = "password123";
        List<String> l = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            l.add(bcrypt.encode(s));
            System.out.println(l.get(i));
        }
        System.out.println("验证：" + bcrypt.matches(s, l.get(0)));
        System.out.println("验证：" + bcrypt.matches(l.get(1), l.get(0)));
    }
}
