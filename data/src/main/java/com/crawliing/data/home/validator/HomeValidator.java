package com.crawliing.data.home.validator;

import com.crawliing.data.home.model.RequestLoginModel;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class HomeValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return RequestLoginModel.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RequestLoginModel model = (RequestLoginModel) target;

        String id = model.getId();
        if(id == null || id.trim().isEmpty()){
            errors.rejectValue("id", "아이디를 입력해주세요");
        }
        
        String pass = model.getPass();
        if(pass == null || pass.trim().isEmpty()){
            errors.rejectValue("pass", "비밀번호를 입력해주세요");

        }
        
    }
}
