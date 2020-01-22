package com.meng.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {

    @GetMapping({"/login", ""})
    public String login(){
        return "login";
    }

//    @GetMapping()
//    public String index(){
//        return "index";
//    }

    @GetMapping("test")
    public String test(){
        return "test";
    }
}
