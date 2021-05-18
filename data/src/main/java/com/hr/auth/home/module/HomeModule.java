package com.hr.auth.home.module;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service("homeModule")
public class HomeModule {
    
    public Map<String,Object> getBindingResultMap(BindingResult result){

        Map<String,Object> errorMap = new HashMap<String,Object>();

        for(FieldError error: result.getFieldErrors()){
            errorMap.put(error.getField(), error.getDefaultMessage());
        }

        return errorMap;
    }
}
