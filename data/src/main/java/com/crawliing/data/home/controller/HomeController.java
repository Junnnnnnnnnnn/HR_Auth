package com.crawliing.data.home.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import com.crawliing.data.home.model.RequestAuthModel;
import com.crawliing.data.home.model.RequestLoginModel;
import com.crawliing.data.home.module.HomeModule;
import com.crawliing.data.home.service.HomeService;
import com.crawliing.data.home.validator.HomeValidator;
import com.fasterxml.jackson.core.sym.Name;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "HomeController", description = "컨트롤러")
@RestController("homeController")
@RequestMapping("/")
public class HomeController {
    
    @Resource(name = "homeService")
    HomeService homeService;

    @GetMapping({"/","index"})
    public ModelMap index(){
        ModelMap modelMap = new ModelMap();
        modelMap = homeService.getTime();
        return modelMap;
    }
    @ApiOperation(
        value = "로그인 확인",
        notes = "admin.gochigo.kr 에 가입되어 있는 회원 확인",
        httpMethod = "POST",
        protocols = "http"
    )
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "로그인 아이디", required = true),
        @ApiImplicitParam(name = "pass", value = "비밀번호", required = true)
    })
    @PostMapping("/login")
    public ModelMap login(@RequestHeader(value="authentication") String token,
                         @Valid @ModelAttribute RequestLoginModel req,
                         BindingResult bindingResult){
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

    @ApiOperation(
        value = "토큰 발급",
        notes = "API 사용을 위한 토큰",
        httpMethod = "POST",
        protocols = "http"
    )
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "토큰 발급 아이디", required = true),
        @ApiImplicitParam(name = "api_key", value = "api 키", required = true)
    })
    @PostMapping("/token")
    public ModelMap token(@Valid @ModelAttribute RequestAuthModel req, BindingResult bindingResult){
        ModelMap modelMap = new ModelMap();
        modelMap = homeService.getToken(req,bindingResult);
        return modelMap;
    }


}
