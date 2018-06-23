package com.echarts.client.bean.twicepack;

import java.io.Serializable;

public class ConfigViewBean implements Serializable {

	private String oldPassword;
	private String newPassword1;
	private int userId;
	private String result;

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword1() {
		return newPassword1;
	}

	public void setNewPassword1(String newPassword1) {
		this.newPassword1 = newPassword1;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public ConfigViewBean(String oldPassword, String newPassword1, int userId) {
		this.oldPassword = oldPassword;
		this.newPassword1 = newPassword1;
		this.userId = userId;
	}
	
	public ConfigViewBean(String result){
		this.result = result;
	}

}
