package com.echarts.client.bean.twicepack;

import java.io.Serializable;
import java.util.List;

import com.echarts.client.bean.Seed;

public class ShopViewBean implements Serializable {
	private int userID;
	private String manageFlag;// ִ���˺��ֲ����ı�ʶ
	/**
	 * �ͻ��������������
	 */
	// ����ũ�������õ��ı���
	private boolean isWantToBuy;
	// �л�ҳ������õ��ı���
	private int page;
	// �������Ӳ����õ��ı���
	private int seedID;
	private int numberResult;
	private int moneyResult;
	/**
	 * �������ش����ͻ���
	 */
	// ����ũ�������õ��ı���
	private int aboutMoney;
	// �л�ҳ������õ��ı���
	private List<Seed> seedList;
	// �������Ӳ����õ��ı���-��

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
