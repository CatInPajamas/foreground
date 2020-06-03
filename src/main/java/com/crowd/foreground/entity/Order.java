package com.crowd.foreground.entity;

import java.util.Date;

public class Order {
    private long id;
    private Integer userid;
    private Integer projectid;
    private Integer itemid;
    private Date createtime;
    private Double money;
    private Integer status;
    private Integer addressid;
    private String remark;
    private String payno;

    public Order(long id, Integer userid, Integer projectid, Integer itemid, Date createtime, Double money, Integer status, Integer addressid, String remark, String payno) {
        this.id = id;
        this.userid = userid;
        this.projectid = projectid;
        this.itemid = itemid;
        this.createtime = createtime;
        this.money = money;
        this.status = status;
        this.addressid = addressid;
        this.remark = remark;
        this.payno = payno;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getProjectid() {
        return projectid;
    }

    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    public Integer getItemid() {
        return itemid;
    }

    public void setItemid(Integer itemid) {
        this.itemid = itemid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAddressid() {
        return addressid;
    }

    public void setAddressid(Integer addressid) {
        this.addressid = addressid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPayno() {
        return payno;
    }

    public void setPayno(String payno) {
        this.payno = payno;
    }
}
