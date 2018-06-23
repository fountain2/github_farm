package com.echarts.client.bean.twicepack;

import java.io.Serializable;
import java.util.List;

import com.echarts.client.bean.Seed;

public class ShopViewBean implements Serializable {
	private int userID;
	private String manageFlag;// 执行了何种操作的标识
	/**
	 * 客户端向服务器传递
	 */
	// 升级农场操作用到的变量
	private boolean isWantToBuy;
	// 切换页面操作用到的变量
	private int page;
	// 购买种子操作用到的变量
	private int seedID;
	private int numberResult;
	private int moneyResult;
	/**
	 * 服务器回传给客户端
	 */
	// 升级农场操作用到的变量
	private int aboutMoney;
	// 切换页面操作用到的变量
	private List<Seed> seedList;
	// 购买种子操作用到的变量-无

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getManageFlag() {
		return manageFlag;
	}

	public void setManageFlag(String manageFlag) {
		this.manageFlag = manageFlag;
	}

	public boolean isWantToBuy() {
		return isWantToBuy;
	}

	public void setWantToBuy(boolean isWantToBuy) {
		this.isWantToBuy = isWantToBuy;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSeedID() {
		return seedID;
	}

	public void setSeedID(int seedID) {
		this.seedID = seedID;
	}

	public int getNumberResult() {
		return numberResult;
	}

	public void setNumberResult(int numberResult) {
		this.numberResult = numberResult;
	}

	public int getMoneyResult() {
		return moneyResult;
	}

	public void setMoneyResult(int moneyResult) {
		this.moneyResult = moneyResult;
	}

	public int getAboutMoney() {
		return aboutMoney;
	}

	public void setAboutMoney(int aboutMoney) {
		this.aboutMoney = aboutMoney;
	}

	public List<Seed> getSeedList() {
		return seedList;
	}

	public void setSeedList(List<Seed> seedList) {
		this.seedList = seedList;
	}
}
