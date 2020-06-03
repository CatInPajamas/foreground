package com.crowd.foreground.entity;

/**
 * 用于首页显示
 */
public class ProjectInfo {
    private Integer id;
    private String name;
    private Double percentage;
    private Integer supportmoney;
    private Integer money;
    private Integer supporter;
    private String head_picture_path;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public Integer getSupportmoney() {
        return supportmoney;
    }

    public void setSupportmoney(Integer supportmoney) {
        this.supportmoney = supportmoney;
    }

    public Integer getSupporter() {
        return supporter;
    }

    public void setSupporter(Integer supporter) {
        this.supporter = supporter;
    }

    public String getHead_picture_path() {
        return head_picture_path;
    }

    public void setHead_picture_path(String head_picture_path) {
        this.head_picture_path = head_picture_path;
    }
}
