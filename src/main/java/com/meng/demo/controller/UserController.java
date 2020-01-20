package com.meng.demo.controller;

import com.meng.demo.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController{

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody User user){
        logger.info(user.toString());
        return new HttpEntity<>(HttpStatus.OK);
    }
}
