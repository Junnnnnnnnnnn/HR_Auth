package com.hr.auth.home.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class RequestLoginModel {
    
    @NotBlank(message = "아이디를 입력하세요.")
    private String id = "";
    @NotEmpty(message = "비밀번호를 입력하세요.")
    private String pass = "";

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
    @Override
    public String toString() {
        return "RequestLoginModel [id=" + id + ", pass=" + pass + "]";
    }

    
    
}
