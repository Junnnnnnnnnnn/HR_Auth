package com.crawliing.data.home.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.crawliing.data.home.dao.HomeDao;
import com.crawliing.data.home.module.HomeModule;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service("homeService")
public class HomeService {
    
    @Resource(name = "homeDao")
    HomeDao homeDao;

    public ModelMap getTime(){
        ModelMap modelMap = new ModelMap();
        modelMap.put("time", homeDao.getTime());
        return modelMap;
    }

    public ModelMap getLoginResult(Map<String,Object> map, BindingResult bindingResult){
        HomeModule module = new HomeModule();
        ModelMap modelMap = new ModelMap();
        if(!bindingResult.hasErrors()){
            if(homeDao.getLoginResult(map) != null){
                modelMap.put("condition", true);
                modelMap.put("MESSGE","로그인 성공");
            }else{
                modelMap.put("condition", false);
                modelMap.put("MESSGE","아이디 혹은 비밀번호가 잘못되었습니다.");
            }
        }else{
            modelMap.put("condition", false);
            modelMap.put("MESSGE", module.getBindingResultMap(bindingResult));
        }
        return modelMap;
    }
}
