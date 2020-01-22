package com.meng.demo.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RSAService {
    //这里其实应该从redis中取出对应秘钥，不过这是demo，就这么写
    public Map<String, String> redisService;

    public RSAService() {
        redisService = new HashMap<>();
    }

    public String getPrivateKey(String userName){
        return redisService.get(userName);
    }

    public String setPrivateKey(String userName, String privateKey){
        return redisService.put(userName, privateKey);
    }
}
