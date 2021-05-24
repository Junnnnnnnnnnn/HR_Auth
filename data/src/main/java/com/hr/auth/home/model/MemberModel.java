package com.hr.auth.home.model;

public class MemberModel {

    private String member_id = null;
	private String member_name = "";
	private String member_pass = null;
	
	private String member_role = "";
	private String member_cert = "";
	
	private String member_site = "";
	private String member_key = "";
	private String member_token = "";
	private String member_photo = "";
	private String member_phone = "";
	
	private String member_addr_basic = "";
	private String member_addr_detail = "";
	
	private String member_status = "";
	private String member_visit = "";
	
	private String shop_code = "";
	
	private double member_pos_x = 0;
	private double member_pos_y = 0;
	
	private String pass_time = "";
	private String cert_time = "";
	private String input_time = "";
    private String update_time = "";

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getMember_pass() {
        return member_pass;
    }

    public void setMember_pass(String member_pass) {
        this.member_pass = member_pass;
    }

    public String getMember_role() {
        return member_role;
    }

    public void setMember_role(String member_role) {
        this.member_role = member_role;
    }

    public String getMember_cert() {
        return member_cert;
    }

    public void setMember_cert(String member_cert) {
        this.member_cert = member_cert;
    }

    public String getMember_site() {
        return member_site;
    }

    public void setMember_site(String member_site) {
        this.member_site = member_site;
    }

    public String getMember_key() {
        return member_key;
    }

    public void setMember_key(String member_key) {
        this.member_key = member_key;
    }

    public String getMember_token() {
        return member_token;
    }

    public void setMember_token(String member_token) {
        this.member_token = member_token;
    }

    public String getMember_photo() {
        return member_photo;
    }

    public void setMember_photo(String member_photo) {
        this.member_photo = member_photo;
    }

    public String getMember_phone() {
        return member_phone;
    }

    public void setMember_phone(String member_phone) {
        this.member_phone = member_phone;
    }

    public String getMember_addr_basic() {
        return member_addr_basic;
    }

    public void setMember_addr_basic(String member_addr_basic) {
        this.member_addr_basic = member_addr_basic;
    }

    public String getMember_addr_detail() {
        return member_addr_detail;
    }

    public void setMember_addr_detail(String member_addr_detail) {
        this.member_addr_detail = member_addr_detail;
    }

    public String getMember_status() {
        return member_status;
    }

    public void setMember_status(String member_status) {
        this.member_status = member_status;
    }

    public String getMember_visit() {
        return member_visit;
    }

    public void setMember_visit(String member_visit) {
        this.member_visit = member_visit;
    }

    public String getShop_code() {
        return shop_code;
    }

    public void setShop_code(String shop_code) {
        this.shop_code = shop_code;
    }

    public double getMember_pos_x() {
        return member_pos_x;
    }

    public void setMember_pos_x(double member_pos_x) {
        this.member_pos_x = member_pos_x;
    }

    public double getMember_pos_y() {
        return member_pos_y;
    }

    public void setMember_pos_y(double member_pos_y) {
        this.member_pos_y = member_pos_y;
    }

    public String getPass_time() {
        return pass_time;
    }

    public void setPass_time(String pass_time) {
        this.pass_time = pass_time;
    }

    public String getCert_time() {
        return cert_time;
    }

    public void setCert_time(String cert_time) {
        this.cert_time = cert_time;
    }

    public String getInput_time() {
        return input_time;
    }

    public void setInput_time(String input_time) {
        this.input_time = input_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    @Override
    public String toString() {
        return "ResponseMemberModel [cert_time=" + cert_time + ", input_time=" + input_time + ", member_addr_basic="
                + member_addr_basic + ", member_addr_detail=" + member_addr_detail + ", member_cert=" + member_cert
                + ", member_id=" + member_id + ", member_key=" + member_key + ", member_name=" + member_name
                + ", member_pass=" + member_pass + ", member_phone=" + member_phone + ", member_photo=" + member_photo
                + ", member_pos_x=" + member_pos_x + ", member_pos_y=" + member_pos_y + ", member_role=" + member_role
                + ", member_site=" + member_site + ", member_status=" + member_status + ", member_token=" + member_token
                + ", member_visit=" + member_visit + ", pass_time=" + pass_time + ", shop_code=" + shop_code
                + ", update_time=" + update_time + "]";
    }
    
    
    

}