package com.crawliing.data.home.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import com.crawliing.data.home.model.RequestLoginModel;
import com.crawliing.data.home.service.HomeService;
import com.crawliing.data.home.validator.HomeValidator;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.HttpMethod;

@Api(value = "HomeController", description = "컨트롤러")
@RestController("homeController")
@RequestMapping("/")
public class HomeController {
    
    @Resource(name = "homeService")
    HomeService homeService;

    @InitBinder
    protected void initBinder(WebDataBinder binder){
        binder.setValidator(new HomeValidator());
    }

    @GetMapping({"/","index"})
    public ModelMap index(){
        ModelMap modelMap = new ModelMap();
        modelMap = homeService.getTime();
        return modelMap;
    }
    @ApiOperation(
        value = "로그인 확인",
        notes = "admin.gochigo.kr 에 가입되어 있는 회원 확인",
        httpMethod = "GET",
        protocols = "http"
    )
    @GetMapping("login")
    public ModelMap login(@Validated @ModelAttribute RequestLoginModel req, BindingResult bindingResult){
        Map<String,Object> map = new HashMap<String,Object>(){
            {
                put("id", req.getId());
                put("pass", req.getPass());
            }
        };
        ModelMap modelMap = new ModelMap();
        modelMap = homeService.getLoginResult(map,bindingResult);
        return modelMap;
    }


}
