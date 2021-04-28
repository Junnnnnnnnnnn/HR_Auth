package com.crawliing.data.home.controller;

import javax.annotation.Resource;

import com.crawliing.data.home.service.HomeService;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
