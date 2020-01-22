package com.meng.demo.service;

import com.meng.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserService {
    @Autowired
    private BCryptPasswordEncoder bcrypt;

    public boolean login(User user){
        //这里先假装从数据库中取值验证，之后改
        return true;
    }

    public int saveUser(User user){
        User u = user;
        System.out.println("加密前 " + user);
        u.setPassword(bcrypt.encode(u.getPassword()));
        System.out.println("加密后 " + u);
        //下面将加密后的结果存数据库即可

        return 0;
    }
}
