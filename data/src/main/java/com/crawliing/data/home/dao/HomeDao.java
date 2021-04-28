package com.crawliing.data.home.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("homeDao")
public interface HomeDao {
    public String getTime();
}
