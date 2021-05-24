package com.hr.auth.home.dao;

import java.util.Map;

import com.hr.auth.home.model.MemberModel;
import com.hr.auth.home.model.RequestAuthModel;
import com.hr.auth.home.model.RequestLoginModel;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("homeDao")
public interface HomeDao {
    public String getTime();
    public Map<String,Object> getLoginResult(RequestLoginModel req);
    public int updateToken(Map<String,Object> map);
    public int ckApiKey(String req);
    public MemberModel getMember(String member_id);
    public String getMemberId(String refreshToken);
    public int insertMember(MemberModel model);
    public int updateMemberStatus(String member_status, String member_id);
    public int deleteMember(String member_id);
}
