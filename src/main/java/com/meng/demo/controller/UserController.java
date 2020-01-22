package com.meng.demo.controller;

import com.meng.demo.pojo.User;
import com.meng.demo.service.RSAService;
import com.meng.demo.service.UserService;
import com.meng.demo.utils.JWTUtil;
import com.meng.demo.utils.RSAUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController{

    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private RSAService rsaService;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody User user) throws Exception {
        System.out.println("登录");
        //测试能否解密
        String tmp = user.getPassword();
        System.out.println("解密前" + tmp);
        tmp = RSAUtil.decrypt(tmp, rsaService.redisService.get(user.getUsername()));
        System.out.println("解密后" + tmp);

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


    @PutMapping()
    public HttpEntity<?> update(@RequestBody User user){
        //判断权限
        String token = (String)request.getAttribute("admin_token");
        if(null == token || "".equals(token)){
            Map map = new HashMap();
            map.put("msg", "权限不足");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }


        System.out.println("成功更新" + user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public HttpEntity<?> search(){
        System.out.println("查询成功");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
