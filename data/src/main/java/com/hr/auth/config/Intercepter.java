package com.hr.auth.config;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.message.AuthException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class Intercepter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        List<String> urls = new ArrayList<>();
        urls.add("swagger");
        urls.add("favicon");
        for(String url: urls){
            if(request.getRequestURI().toString().contains(url)){
                return true;
            }
        }

        System.out.println("Authorization :::: " + request.getHeader("Authorization"));
        if(request.getHeader("Authorization") == "1234"){
            return true;
        }else{
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED); 
            return false;
        }
    }

}
