package com.meng.demo.pojo;

import lombok.Data;

@Data
public class ErrorInfo<T> {
    public static final Integer ERROR = 100;

    private Integer code;
    private String message;
    private String url;
    private T data;
}
