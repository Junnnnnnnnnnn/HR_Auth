package com.hr.auth.home.dao;

import java.util.Map;

import com.hr.auth.home.model.RequestAuthModel;
import com.hr.auth.home.model.RequestLoginModel;
import com.hr.auth.home.model.ResponseMemberModel;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("homeDao")
public interface HomeDao {
    public String getTime();
    public Map<String,Object> getLoginResult(RequestLoginModel req);
    public int updateToken(Map<String,Object> map);
    public int ckApiKey(String req);
    public ResponseMemberModel getMember(String member_id);
    public String getMemberId(String refreshToken);

}
