package com.hr.auth.home.model;

import javax.validation.constraints.NotBlank;

public class RequestAuthModel {

    @NotBlank(message="아이디를 입력해주세요")
    private String id = "";
    @NotBlank(message="패스워드를 입력해주세요")
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
        return "RequestAuthModel [id=" + id + ", pass=" + pass + "]";
    }

}