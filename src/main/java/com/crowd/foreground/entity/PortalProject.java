package com.crowd.foreground.entity;

/**
 * 实体类 项目
 */
public class PortalProject {
    private Integer projectId;
    private String projectname;
    private String picturePath;
    private Integer money;
    private String deployData;
    private String percentage;
    private Integer supporter;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getDeployData() {
        return deployData;
    }

    public void setDeployData(String deployData) {
        this.deployData = deployData;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public Integer getSupporter() {
        return supporter;
    }

    public void setSupporter(Integer supporter) {
        this.supporter = supporter;
    }
}
