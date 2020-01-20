package com.meng.demo.controller;

import com.meng.demo.pojo.User;
import com.meng.demo.service.UserService;
import com.meng.demo.utils.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController{

    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private UserService userService;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody User user) throws NoSuchAlgorithmException {
        logger.info(user.toString());
        if(userService.login(user)){
            Map map = new HashMap();
            map.put("token", jwtUtil.generateToken(user));
            map.put("username", user.getUsername());
            logger.info(map.toString());
            return new ResponseEntity<>(map, HttpStatus.OK);
        }else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
