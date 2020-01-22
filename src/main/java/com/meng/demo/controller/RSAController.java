package com.meng.demo.controller;

import com.meng.demo.pojo.User;
import com.meng.demo.service.RSAService;
import com.meng.demo.utils.RSAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RSAController {
    @Autowired
    private RSAService rsaService;

    @PostMapping("/rsa")
    public HttpEntity<?> RSA(@RequestBody User user) throws Exception {
        System.out.println(user);
        var map2 = RSAUtil.generateKeyPair2();
        //System.out.println(map2);
        for(var k:map2.entrySet()){
            System.out.println("键：" + k.getKey() + ", " + "值：" + k.getValue());
        }
        Map map = new HashMap();
        map.put("publicKey", map2.get("publicKey"));
        rsaService.setPrivateKey(user.getUsername(), map2.get("privateKey"));
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
