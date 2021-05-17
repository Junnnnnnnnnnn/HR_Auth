package com.hr.auth.home.module;

import java.math.BigInteger;
import java.security.MessageDigest;
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

    public String getSHA512(String input) {

        String toReturn = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            digest.reset();
            digest.update(input.getBytes("utf8"));
            toReturn = String.format("%0128x", new BigInteger(1, digest.digest()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return toReturn;
    }

}
