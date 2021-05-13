package com.crawliing.data.home.module;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class HomeModule {
    
    public Map<String,Object> getBindingResultMap(BindingResult result){

        Map<String,Object> errorMap = new HashMap<String,Object>();

        for(FieldError error: result.getFieldErrors()){
            errorMap.put(error.getField(), error.getDefaultMessage());
        }

        return errorMap;
    }

}
