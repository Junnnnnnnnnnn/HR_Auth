package com.crawliing.data.home.model;

public class RequestLoginModel {
    
    private String id = "";
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
