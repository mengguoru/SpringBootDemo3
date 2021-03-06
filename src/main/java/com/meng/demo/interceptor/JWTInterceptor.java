package com.meng.demo.interceptor;

import com.meng.demo.controller.UserController;
import com.meng.demo.exception.MyException;
import com.meng.demo.utils.JWTUtil;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;

@Component
public class JWTInterceptor implements HandlerInterceptor {
    private final Logger logger = LoggerFactory.getLogger(JWTInterceptor.class);

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("经过拦截器");
        String token = request.getHeader("Authorization");

//        Claims claims = jwtUtil.parseJWT(token);
//        String auth = (String) claims.get("authority");
//
//        if(null != auth && auth.equals("admin")){
//            System.out.println("token为：" + token);
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
//            String tmp = format.format(claims.getExpiration());
//            System.out.println("过期时间：" + tmp);
//            request.setAttribute("admin_token", token);
//        }
        try {
            Claims claims = jwtUtil.parseJWT(token);
            String auth = (String) claims.get("authority");

            if(null != auth && auth.equals("admin")){
                System.out.println("token为：" + token);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
                String tmp = format.format(claims.getExpiration());
                System.out.println("过期时间：" + tmp);
                request.setAttribute("admin_token", token);
            }
        }catch (Exception e){
            //e.printStackTrace();
            //throw e;
            //throw new MyException("token错误");
            throw new MyException("token错误", e);
        }

        return true;
    }
}
