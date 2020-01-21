package com.meng.demo.exception;

import com.meng.demo.controller.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyException extends Exception {
    private final Logger logger = LoggerFactory.getLogger(MyException.class);

    public MyException(String msg){
        //printStackTrace();
        super(msg);
    }

    public MyException(Exception e){
        super(e);
        e.printStackTrace();
    }

    public MyException(String msg, Exception e){
        super(msg, e);
        //e.printStackTrace();
        //logger.error("error", this);
        printStackTrace();
    }
}
