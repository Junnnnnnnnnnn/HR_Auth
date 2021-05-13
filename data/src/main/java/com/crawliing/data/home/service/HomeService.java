package com.crawliing.data.home.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import com.crawliing.data.home.dao.HomeDao;
import com.crawliing.data.home.model.RequestAuthModel;
import com.crawliing.data.home.module.HomeModule;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service("homeService")
public class HomeService {
    
    @Resource(name = "homeDao")
    private HomeDao homeDao;
    private HomeModule module = new HomeModule();

    public ModelMap getTime(){
        ModelMap modelMap = new ModelMap();
        modelMap.put("time", homeDao.getTime());
        return modelMap;
    }

    public ModelMap getLoginResult(Map<String,Object> map, BindingResult bindingResult){
        ModelMap modelMap = new ModelMap();
        if(!bindingResult.hasErrors()){
            if(homeDao.getLoginResult(map) != null){
                modelMap.put("condition", true);
                modelMap.put("meassge","로그인 성공");
            }else{
                modelMap.put("condition", false);
                modelMap.put("meassge","아이디 혹은 비밀번호가 잘못되었습니다.");
            }
        }else{
            modelMap.put("condition", false);
            modelMap.put("meassge", module.getBindingResultMap(bindingResult));
        }
        return modelMap;
    }

    public ModelMap getToken(RequestAuthModel model, BindingResult bindingResult){
        ModelMap modelMap = new ModelMap();
        UUID token = UUID.randomUUID();
        if(!bindingResult.hasErrors()){
            System.out.println(model.getApi_key());
            if(model.getApi_key().equals("1234")){
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("id", model.getId());
                map.put("api_key",model.getApi_key());
                map.put("token", token.toString());
                map.put("date",addDateHour(10));
                if(homeDao.updateToken(map) == 1){
                    modelMap.put("condition", true);
                    modelMap.put("message","토큰 발급 완료");
                    modelMap.put("token", token.toString());
                }else{
                    modelMap.put("condition", false);
                    modelMap.put("message","데이터베이스 커넥션 에러");
                    modelMap.put("token", null);
                }
            }else{
                modelMap.put("condition", false);
                modelMap.put("message","api 키가 다릅니다.");
                modelMap.put("token", null);
            }
        }else{
            modelMap.put("condition",false);
            modelMap.put("message",module.getBindingResultMap(bindingResult));
            modelMap.put("token",null);
        }

        return modelMap;
    }

    public String addDateHour(int hour){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String expireDate = "";
        Calendar cal = Calendar.getInstance();

        cal.setTime(date);
        cal.add(Calendar.HOUR, hour);
        expireDate = sdf.format(cal.getTime());

        return expireDate;
    }
}
