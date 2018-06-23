package com.echarts.client.bean.twicepack;

import java.io.Serializable;

import com.echarts.client.bean.Field;
import com.echarts.client.bean.Repository;
import com.echarts.client.bean.User;

public class FarmViewBean implements Serializable{
	private User user;
	private Field field;
	private int field_toStatus;
	
	private Repository repository;
	private String operationFlag;
	
	
	
	public FarmViewBean(String operationFlag) {
		this.operationFlag = operationFlag;
	}

	public FarmViewBean(User user, String operationFlag) {
		this.user = user;
		this.operationFlag = operationFlag;
	}

	public FarmViewBean(Field field, String operationFlag) {
		this.field = field;
		this.operationFlag = operationFlag;
	}
	
	public FarmViewBean(Repository repository, String operationFlag) {
		this.repository = repository;
		this.operationFlag = operationFlag;
	}

	public FarmViewBean(Field field, int field_toStatus, String operationFlag) {
		this.field = field;
		this.field_toStatus=field_toStatus;
		this.operationFlag = operationFlag;
	}
	
	public FarmViewBean(User user, Field field, String operationFlag) {
		this.user = user;
		this.field = field;
		this.operationFlag = operationFlag;
	}

	
	public Repository getRepository() {
		return repository;
	}

	public void setRepository(Repository repository) {
		this.repository = repository;
	}

	public int getField_toStatus() {
		return field_toStatus;
	}

	public void setField_toStatus(int field_toStatus) {
		this.field_toStatus = field_toStatus;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getOperationFlag() {
		return operationFlag;
	}

	public void setOperationFlag(String operationFlag) {
		this.operationFlag = operationFlag;
	}
	
}
