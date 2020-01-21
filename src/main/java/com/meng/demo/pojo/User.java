package com.meng.demo.pojo;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    //权限级别
    private String authority;
}
