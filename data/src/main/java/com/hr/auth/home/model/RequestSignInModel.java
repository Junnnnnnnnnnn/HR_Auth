package com.hr.auth.home.model;

public class RequestSignInModel {

    private String member_id = "";
    private String member_pass = "";
    private String member_phone = "";
    private String member_photo = "";

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getMember_pass() {
        return member_pass;
    }

    public void setMember_pass(String member_pass) {
        this.member_pass = member_pass;
    }

    public String getMember_phone() {
        return member_phone;
    }

    public void setMember_phone(String member_phone) {
        this.member_phone = member_phone;
    }

    public String getMember_photo() {
        return member_photo;
    }

    public void setMember_photo(String member_photo) {
        this.member_photo = member_photo;
    }

    @Override
    public String toString() {
        return "RequestSignInModel [member_id=" + member_id + ", member_pass=" + member_pass + ", member_phone="
                + member_phone + ", member_photo=" + member_photo + "]";
    }
}