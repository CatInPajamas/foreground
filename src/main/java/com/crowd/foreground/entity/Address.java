package com.crowd.foreground.entity;

public class Address {
    private Integer id;
    private Integer userid;
    private String tel;
    private String address;
    private String realname;

    public Address(Integer userid, String tel, String address, String realname) {
        this.userid = userid;
        this.tel = tel;
        this.address = address;
        this.realname = realname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    @Override
    public String toString() {
        return "收件人："+realname+" 联系方式："+tel+" 详细地址："+address;
    }
}
