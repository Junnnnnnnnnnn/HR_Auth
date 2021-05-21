package com.hr.auth.config;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.security.auth.message.AuthException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class Intercepter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Authorization");

        
        List<String> urls = new ArrayList<>();
        urls.add("swagger");
        urls.add("favicon");
        for(String url: urls){
            if(request.getRequestURI().toString().contains(url)){
                return true;
            }
        }


        //Map<String, String> map = new HashMap<String, String>();

        // Enumeration headerNames = request.getHeaderNames();
        // while (headerNames.hasMoreElements()) {
        //     String key = (String) headerNames.nextElement();
        //     String value = request.getHeader(key);
            
        //     System.out.println("HEADER : "+key+" ::: "+value);
        // }


        // System.out.println("A:::::::::::::::::::::::::: "+request.getRemoteAddr());
        // System.out.println("B:::::::::::::::::::::::::: "+request.getRemotePort());  
        // System.out.println("C:::::::::::::::::::::::::: "+request.getRemoteHost());  

        // System.out.println("REQEUST.GETHEADRE ::: " + request.getHeader("Authorization"));
        // System.out.println("REQEUST.GETMETHOD ::: " + request.getMethod());
        if(request.getHeader("Authorization") != null && request.getHeader("Authorization").equals("1234")){
            return true;
        }else{
            // response.sendError(HttpServletResponse.SC_UNAUTHORIZED); 
            return false;
        }
    }

}
