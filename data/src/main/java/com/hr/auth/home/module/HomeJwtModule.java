package com.hr.auth.home.module;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service("homeJwtModule")
public class HomeJwtModule {

    private static final String SECRET_KEY = "yotdarkSecret";

    @Resource(name = "homeModule")
    private HomeModule module;

    public <T> String createAccessToken(String key, T data){
        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        cal.add(Calendar.MINUTE,30);
        Date exp = cal.getTime();
        String jwt = Jwts.builder()
                         .setHeaderParam("type","JWT")
                         .setHeaderParam("reqDate",System.currentTimeMillis())
                         .setSubject("access")
                         .claim("iat",now)
                         .claim("exp",exp)
                         .claim(key, data)
                         .signWith(SignatureAlgorithm.HS256, this.generateKey())
                         .compact();
        return jwt;
    }
    public <T> String createRefreshToken(String key){
        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        Map<String,Object> map = new HashMap<String,Object>();

        cal.setTime(now);
        cal.add(Calendar.DATE,7);
        Date exp = cal.getTime();

        map.put("iat",now);
        map.put("exp",exp);
        
        String jwt = Jwts.builder()
                         .setHeaderParam("type","JWT")
                         .setHeaderParam("reqDate",System.currentTimeMillis())
                         .setSubject("refresh")
                         .claim(key, map)
                         .signWith(SignatureAlgorithm.HS256, this.generateKey())
                         .compact();
        return jwt;
    }

    public byte[] generateKey(){
        byte[] key = null;
        try{
            key = SECRET_KEY.getBytes("UTF-8");
        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return key;
    }

    public Map<String, Object> get(String token) {
		// HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		// String jwt = request.getHeader("Authorization");
		String jwt = token;
		Jws<Claims> claims = null;
		try {
			claims = Jwts.parser()
						 .setSigningKey(SECRET_KEY.getBytes("UTF-8"))
						 .parseClaimsJws(jwt);
		} catch (Exception e) {
            e.printStackTrace();
			/*개발환경
			Map<String,Object> testMap = new HashMap<>();
			testMap.put("memberId", 2);
			return testMap;*/
		}
		Map<String, Object> value = (Map<String, Object>)claims.getBody();
		return value;
	}


}