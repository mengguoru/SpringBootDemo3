package com.meng.demo;

import com.meng.demo.service.RSAService;
import com.meng.demo.service.UserService;
import com.meng.demo.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.Key;
import java.security.NoSuchAlgorithmException;

@Configuration
public class Configuration1 {
    @Autowired
    private JWTUtil jwtUtil;

    @Bean
    public JWTUtil jwtUtil() throws NoSuchAlgorithmException {
        return new JWTUtil();
    }
    @Bean
    public UserService userService(){return new UserService();}
    @Bean
    public BCryptPasswordEncoder bcrypt(){return new BCryptPasswordEncoder();}
    @Bean
    public RSAService rsaService(){return new RSAService();}
}
