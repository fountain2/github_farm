package com.echarts.client.bean.twicepack;

import java.io.Serializable;

import com.echarts.client.bean.User;

public class LoginViewBean implements Serializable{
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
