package com.hr.auth.home.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hr.auth.home.dao.HomeDao;
import com.hr.auth.home.model.MemberModel;
import com.hr.auth.home.model.RequestAuthModel;
import com.hr.auth.home.module.HomeJwtModule;
import com.hr.auth.home.module.HomeModule;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

@Service("homeService")
public class HomeService {
    
    @Resource(name = "homeDao")
    private HomeDao homeDao;
    @Resource(name = "homeModule")
    private HomeModule module;
    @Resource(name = "homeJwtModule")
    private HomeJwtModule jwt;

    public ModelMap getTime(){
        ModelMap modelMap = new ModelMap();
        modelMap.put("time", homeDao.getTime());
        return modelMap;
    }

    public ModelMap getToken(RequestAuthModel model, BindingResult bindingResult){
        ModelMap modelMap = new ModelMap();
        MemberModel member = homeDao.getMember(model.getId());
        if(!bindingResult.hasErrors()){
            if(member == null){
                modelMap.put("condition", false);
                modelMap.put("message","{'code':'001','message':'등록된 회원이 아닙니다'}");
                modelMap.put("accessToken", null);
                modelMap.put("refreshToken", null);
            }else if(member.getMember_status().equals("E")){
                Long exp = Long.parseLong(String.valueOf(jwt.get(member.getMember_cert()).get("exp")));
                System.out.println(validate(exp));
                modelMap.put("condition", false);
                modelMap.put("accessToken", null);
                modelMap.put("refreshToken", null);
                if(validate(exp)){
                    modelMap.put("message","{'code':'002','message':'인증되지 않은 아이디입니다'}");
                }else{
                    modelMap = deleteMember(model.getId());
                }
            }else{
                String accessToken = new String();
                String refreshToken = jwt.createRefreshToken(model.getId());
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("id", model.getId());
                map.put("pass", model.getPass());
                map.put("token", refreshToken);
                map.put("date",addDateHour(10));
                if(homeDao.updateToken(map) == 1){
                    accessToken = jwt.createAccessToken("member", member);
                    modelMap.put("condition", true);
                    modelMap.put("message","{'code':'000','message':'토큰 발급 완료'}");
                    modelMap.put("accessToken", accessToken);
                    modelMap.put("refreshToken", refreshToken);
                }else{
                    modelMap.put("condition", false);
                    modelMap.put("message","{'code':'003','message':'아이디 혹은 비밀번호가 다름니다'}");
                    modelMap.put("accessToken", null);
                    modelMap.put("refreshToken", null);
                }
            }
        }else{
            modelMap.put("condition",false);
            modelMap.put("message",module.getBindingResultMap(bindingResult));
            modelMap.put("accessToken", null);
            modelMap.put("refreshToken", null);
        }

        return modelMap;
    }
    public ModelMap refreshAccessToken(String access, String refresh){
        ModelMap modelMap = new ModelMap();
        ObjectMapper mapper = new ObjectMapper();
        Long accessExp = Long.parseLong(String.valueOf(jwt.get(access).get("exp")));
        MemberModel member = mapper.convertValue(jwt.get(access).get("member"), MemberModel.class) ;
        if(!validate(accessExp)){
            if(jwt.get(refresh).get(member.getMember_id()) != null && jwt.get(refresh).get(homeDao.getMemberId(refresh)) != null){
                @SuppressWarnings("unchecked")
                Map<String,Object> refreshValiDate = (Map<String,Object>) jwt.get(refresh).get(member.getMember_id());
                Long refreshExp = Long.parseLong(String.valueOf(refreshValiDate.get("exp")));
                if(!validate(refreshExp)){
                    modelMap.put("condition", false);
                    modelMap.put("message","{'code':'001','message':'토큰이 만료되었습니다. 로그인을 통해 재갱신 해야합니다'}");
                    modelMap.put("token", null);
                }else{
                    modelMap.put("condition",true);
                    modelMap.put("message","{'code':'000','message':'토큰이 갱신되었습니다.'}");
                    modelMap.put("token",jwt.createAccessToken("member", member));
                }
            }else{
                modelMap.put("condition",false);
                modelMap.put("message","{'code':'002','message':'토큰이 변조됬거나 휴효하지 않습니다. 로그인을 통해 재갱신 해야합니다'}");
                modelMap.put("token",null);
            }
        }else{
            modelMap.put("condition",true);
            modelMap.put("message","{'code':'000','message':'토큰이 갱신되었습니다.'}");
            modelMap.put("token",jwt.createAccessToken("member", member));
        }
        return modelMap;
    }

    public ModelMap signUp(MemberModel model){
        ModelMap modelMap = new ModelMap();
        if(homeDao.getMember(model.getMember_id()) == null){
            String token = jwt.createAccessToken("email", model.getMember_id());
            String[] emails = {model.getMember_id()};
            model.setMember_cert(token);
            if(module.sendNaverMail("[함께라면] 회원가입 인증메일", "아래 문장을 복사한뒤 붙혀 넣으세요<br>" + token, emails)){
                if(homeDao.insertMember(model) > 0){
                    modelMap.put("condition", true);
                    modelMap.put("message","{'code':'000','message':'인증 메일이 발송 되었습니다'}");
                }else{
                    modelMap.put("condition", false);
                    modelMap.put("message","{'code':'099','message':'시스템 오류입니다'}");
                }
            }else{
                modelMap.put("condition", false);
                modelMap.put("message","{'code':'001','message':'메일 발송 오류입니다 메일을 확인해주세요'}");
            }
        }else{
            modelMap.put("condition", false);
            modelMap.put("message","{'code':'002','message':'이미 등록된 회원입니다'}");
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

    public ModelMap statusToken(String access){
        ModelMap modelMap = new ModelMap();
        if(jwt.get(access).get("exp") != null){
            Long exp = Long.parseLong(String.valueOf(jwt.get(access).get("exp")));
            modelMap.put("condition", validate(exp));
            modelMap.put("message", "{'code':'000','message':'토큰을 정상적으로 확인 했습니다'}");
            modelMap.put("remain",remainTime(exp));
        }else{
            modelMap.put("condition", false);
            modelMap.put("message", "{'code':'001','message':'토큰이 회손되었거나 변조 되었습니다'}");
            modelMap.put("remain",null);
        }
        return modelMap;
    }

    public boolean validate(Long exp){
        boolean condition = false;
        Long now = System.currentTimeMillis();

        if(now < exp){
            condition = true;
        }

        return condition;
    }

    public ModelMap remainTime(Long exp){
        ModelMap modelMap = new ModelMap();
        SimpleDateFormat sdfMm = new SimpleDateFormat("mm");
        SimpleDateFormat sdfSs = new SimpleDateFormat("s");
        Long now = System.currentTimeMillis();
        Long remain = now < exp? exp - now : 0;
        
        modelMap.put("mimute", sdfMm.format(remain));
        modelMap.put("second", sdfSs.format(remain));
        return modelMap;
    }

    public ModelMap ckEmailToken(String token){
        ModelMap modelMap = new ModelMap();
        Long exp = Long.parseLong(String.valueOf(jwt.get(token).get("exp")));
        String member_id = String.valueOf(jwt.get(token).get("email"));
        if(validate(exp)){
            if(homeDao.updateMemberStatus("Y",member_id) > 0){
                modelMap.put("condition", true);
                modelMap.put("message", "{'code':'000','message':'정상적으로 가입 되었습니다'}" );
            }else{
                modelMap.put("condition", false);
                modelMap.put("message", "{'code':'099','message':'시스템 오류'}");
            }
        }else{
            modelMap = deleteMember(member_id);
        }
        return modelMap;
    }

    public ModelMap deleteMember(String member_id){
        ModelMap modelMap = new ModelMap();
        if(homeDao.deleteMember(member_id) > 0){
            modelMap.put("condition", false);
            modelMap.put("message", "{'code':'001','message':'인증메일이 만료되었습니다. 회원가입을 다시 해주세요'}");
        }else{
            modelMap.put("condition", false);
            modelMap.put("message", "{'code':'099','message':'시스템 오류'}");
        }
        return modelMap;
    }

}
