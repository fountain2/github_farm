package com.echarts.client.bean;

import java.io.Serializable;

public class Repository implements Serializable{
	private String resGoodsName;
	
    private Integer resId;

    private Integer userId;

    private Integer resGoodsId;

    private Integer resType;

    private Integer resNumber;
    
    private Integer userMoney;
    
    private Integer resPrice;
    
    private String resBrief;
    
    private String userName;
    
    public Repository() {
	}

	public Repository(Integer resId, Integer userId, Integer resGoodsId, Integer resType, Integer resNumber) {
		this.resId = resId;
		this.userId = userId;
		this.resGoodsId = resGoodsId;
		this.resType = resType;
		this.resNumber = resNumber;
	}

    public Integer getResId() {
        return resId;
    }

    public void setResId(Integer resId) {
        this.resId = resId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getResGoodsId() {
        return resGoodsId;
    }

    public void setResGoodsId(Integer resGoodsId) {
        this.resGoodsId = resGoodsId;
    }

    public Integer getResType() {
        return resType;
    }

    public void setResType(Integer resType) {
        this.resType = resType;
    }

    public Integer getResNumber() {
        return resNumber;
    }

    public void setResNumber(Integer resNumber) {
        this.resNumber = resNumber;
    }

	public Integer getUserMoney() {
		return userMoney;
	}

	public void setUserMoney(Integer userMoney) {
		this.userMoney = userMoney;
	}

	public Integer getResPrice() {
		return resPrice;
	}

	public void setResPrice(Integer resPrice) {
		this.resPrice = resPrice;
	}

	public String getResGoodsName() {
		return resGoodsName;
	}

	public void setResGoodsName(String resGoodsName) {
		this.resGoodsName = resGoodsName;
	}

	public String getResBrief() {
		return resBrief;
	}

	public void setResBrief(String resBrief) {
		this.resBrief = resBrief;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}