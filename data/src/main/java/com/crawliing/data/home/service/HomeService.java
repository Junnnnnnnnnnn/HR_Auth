package com.crawliing.data.home.service;

import javax.annotation.Resource;

import com.crawliing.data.home.dao.HomeDao;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

@Service("homeService")
public class HomeService {
    
    @Resource(name = "homeDao")
    HomeDao homeDao;

    public ModelMap getTime(){
        ModelMap modelMap = new ModelMap();

        modelMap.put("time", homeDao.getTime());

        return modelMap;
    }


}
