package com.hr.auth.home.model;

import javax.validation.constraints.NotBlank;

public class RequestAuthModel {

    @NotBlank(message="아이디를 입력해주세요")
    private String id = "";
    @NotBlank(message="패스워드를 입력해주세요")
    private String pass = "";
    @NotBlank(message="api 키를 입력하세요")
    private String api_key = "";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "RequestAuthModel [api_key=" + api_key + ", id=" + id + ", pass=" + pass + "]";
    }

}