package com.meng.demo.service;

import com.meng.demo.Configuration1;
import com.meng.demo.pojo.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = Configuration1.class)
@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    void saveUser() {
        User u = new User();
        u.setUsername("小明");
        u.setPassword("pwd123");
        userService.saveUser(u);
    }
}