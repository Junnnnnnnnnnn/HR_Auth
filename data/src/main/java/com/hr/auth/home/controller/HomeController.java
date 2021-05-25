package com.hr.auth.home.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.hr.auth.home.model.MemberModel;
import com.hr.auth.home.model.RequestAuthModel;
import com.hr.auth.home.service.HomeService;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

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
        value = "토큰 발급",
        notes = "API 사용을 위한 토큰",
        httpMethod = "POST",
        protocols = "http"
    )
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "아이디", required = true),
        @ApiImplicitParam(name = "pass", value = "패스워드", required = true),
    })
    @PostMapping("/token")
    public ModelMap token(@Valid @ModelAttribute RequestAuthModel req, BindingResult bindingResult, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Authorization");

        System.out.println("--------------------------------------------||token");

        ModelMap modelMap = new ModelMap();
        modelMap = homeService.getToken(req,bindingResult);
        return modelMap;
    }

    @ApiOperation(
        value = "토큰 재발급",
        notes = "access 토큰 만료 시 재갱신",
        httpMethod = "POST",
        protocols = "http"
    )
    @ApiImplicitParams({
        @ApiImplicitParam(name = "access", value = "access token", required = true),
        @ApiImplicitParam(name = "refresh", value = "refresh token", required = true)
    })
    @PostMapping("/refreshAccessToken")
    public ModelMap refreshAccessToken(@RequestParam String access,
                                       @RequestParam String refresh){
        ModelMap modelMap = new ModelMap();
        modelMap = homeService.refreshAccessToken(access,refresh);
        return modelMap;
    }

    @ApiOperation(
        value = "토큰 상태 확인",
        notes = "토큰 상태확인을 condition / remain 으로 확인 할 수 있다.",
        httpMethod = "POST",
        protocols = "http"
    )
    @ApiImplicitParam(name = "access", value = "Access Token", required = true)
    @PostMapping("/statusToken")
    public ModelMap statusToken(@RequestParam String access){
        ModelMap modelMap = new ModelMap();
        modelMap = homeService.statusToken(access);
        return modelMap;
    }

    @ApiOperation(
        value = "회원가입",
        notes = "값을 받아 인증 토큰을 naver smtp를 통해 전달",
        httpMethod = "POST",
        protocols = "http"
    )
    @ApiImplicitParams({
        @ApiImplicitParam(name = "member_id", value="아이디", required = true),
        @ApiImplicitParam(name = "member_pass", value="비밀번호", required = true),
        @ApiImplicitParam(name = "member_name", value="이름", required = true),
        @ApiImplicitParam(name = "member_phone", value="전화번호", required = true),
        @ApiImplicitParam(name = "member_photo", value="프로필 사진", required = false),
    })
    @PostMapping("/signUp")
    public ModelMap signUp (@ModelAttribute MemberModel model){
        ModelMap modelMap = new ModelMap();
        modelMap = homeService.signUp(model);
        return modelMap;
    }

    @ApiOperation(
        value = "회원가입 인증 토큰 체크",
        notes = "메일로 보낸 인증 토큰을 체크하여 정식 사용자로 변환",
        httpMethod = "POST",
        protocols = "Http"
    )
    @ApiImplicitParam(name = "token", value="토큰", required = true)
    @PostMapping("/ckEmailToken")
    public ModelMap ckEmailToken(@RequestParam String token){
        ModelMap modelMap = new ModelMap();
        modelMap = homeService.ckEmailToken(token);
        return modelMap;
    }
}
