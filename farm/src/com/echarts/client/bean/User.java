package com.echarts.client.bean;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{
    private Integer userId;

    private String userName;

    private String userPaswword;

    private Integer userLv;

    private Integer userMoney;

    private Integer userType;

    private Date userAccesstime;
    
    

    public User() {
	}

	public User(Integer userId, String userName, String userPaswword, Integer userLv, Integer userMoney,
			Integer userType, Date userAccesstime) {
		this.userId = userId;
		this.userName = userName;
		this.userPaswword = userPaswword;
		this.userLv = userLv;
		this.userMoney = userMoney;
		this.userType = userType;
		this.userAccesstime = userAccesstime;
	}

	public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPaswword() {
        return userPaswword;
    }

    public void setUserPaswword(String userPaswword) {
        this.userPaswword = userPaswword == null ? null : userPaswword.trim();
    }

    public Integer getUserLv() {
        return userLv;
    }

    public void setUserLv(Integer userLv) {
        this.userLv = userLv;
    }

    public Integer getUserMoney() {
        return userMoney;
    }

    public void setUserMoney(Integer userMoney) {
        this.userMoney = userMoney;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Date getUserAccesstime() {
        return userAccesstime;
    }

    public void setUserAccesstime(Date userAccesstime) {
        this.userAccesstime = userAccesstime;
    }
}