package com.echarts.client.bean.twicepack;

import java.io.Serializable;

public class RepositoryViewBean implements Serializable{
	private int userID;
	private String userName;
	private int userMoney;
	private int page;
	private String action;
	private int useNumber;
	private int resID;
	private int resGoodsID;
	private int resPrice;
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public int getUseNumber() {
		return useNumber;
	}
	public void setUseNumber(int useNumber) {
		this.useNumber = useNumber;
	}
	public int getResID() {
		return resID;
	}
	public void setResID(int resID) {
		this.resID = resID;
	}
	public int getResGoodsID() {
		return resGoodsID;
	}
	public void setResGoodsID(int resGoodsID) {
		this.resGoodsID = resGoodsID;
	}
	public int getResPrice() {
		return resPrice;
	}
	public void setResPrice(int resPrice) {
		this.resPrice = resPrice;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserMoney() {
		return userMoney;
	}
	public void setUserMoney(int userMoney) {
		this.userMoney = userMoney;
	}

}
