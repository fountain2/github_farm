package com.echarts.client.bean.twicepack;

import java.io.Serializable;

import com.echarts.client.bean.User;

public class RegisterViewBean implements Serializable{
	private User user;
	private String operation;
	
	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
