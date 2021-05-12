package com.crawliing.data.home.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("homeDao")
public interface HomeDao {
    public String getTime();
    public Map<String,Object> getLoginResult(Map<String,Object> map);
}
