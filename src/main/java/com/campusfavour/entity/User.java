package com.campusfavour.entity;

import java.util.Date;

/*
* User表
* */
public class User {
    //用户表主键
    String id;
    //用户名
    String userName;
    //密码
    String password;
    //姓名
    String name;
    //手机号
    String phone;
    //学号
    String stuNumber;
    //创建时间
    Date createTime;
    //总完成任务数 默认0
    String taskCount;
    //当月完成任务数 默认0
    String taskCountMonth;
    //任务发布数量 默认0
    String releaseCount;
    //信誉分，默认100
    int creditScore;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(String taskCount) {
        this.taskCount = taskCount;
    }

    public String getTaskCountMonth() {
        return taskCountMonth;
    }

    public void setTaskCountMonth(String taskCountMonth) {
        this.taskCountMonth = taskCountMonth;
    }

    public String getReleaseCount() {
        return releaseCount;
    }

    public void setReleaseCount(String releaseCount) {
        this.releaseCount = releaseCount;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStuNumber() {
        return stuNumber;
    }

    public void setStuNumber(String stuNumber) {
        this.stuNumber = stuNumber;
    }
}
