package com.hr.auth.home.module;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.hr.auth.config.PropertiesUtil;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service("homeModule")
public class HomeModule {
    
    @Resource(name="propertiesUtil")
    private PropertiesUtil util;

    public Map<String,Object> getBindingResultMap(BindingResult result){

        Map<String,Object> errorMap = new HashMap<String,Object>();

        for(FieldError error: result.getFieldErrors()){
            errorMap.put(error.getField(), error.getDefaultMessage());
        }

        return errorMap;
    }

    public boolean sendNaverMail(String sub, String content,String[] emails){
        
        boolean condition = false;
        String id = util.getNaverId();
        String pass = util.getNaverPass();

        Properties props = new Properties();
        props.put("mail.smtp.host", util.getNaverHost());
        props.put("mail.smtp.port", util.getNaverPort());
        props.put("mail.smtp.auth", true);
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(id, pass);
            }
        });

        try {
            InternetAddress[] addr = new InternetAddress[emails.length];
            for(int i = 0; i < emails.length; i++){
                addr[i] = new InternetAddress(emails[i]);
            }
            
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(id));
            message.addRecipients(Message.RecipientType.TO, addr);
            message.setSubject(sub);
            message.setText(content);
            message.setHeader("content-Type", "text/html");

            Transport.send(message);
            System.out.println("SEND MESSAGE!");
            condition = true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return condition;
    }

}
