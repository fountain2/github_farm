package com.echarts.client.bean;

import java.io.Serializable;

import com.echarts.client.bean.twicepack.ConfigViewBean;
import com.echarts.client.bean.twicepack.FarmViewBean;
import com.echarts.client.bean.twicepack.LoginViewBean;
import com.echarts.client.bean.twicepack.OtherFarmViewBean;
import com.echarts.client.bean.twicepack.RegisterViewBean;
import com.echarts.client.bean.twicepack.RepositoryViewBean;
import com.echarts.client.bean.twicepack.ShopViewBean;

public class Message implements Serializable{
	//登录页面bean
	private LoginViewBean loginViewBean; 
	//注册页面bean
	private RegisterViewBean registerViewBean;
	//农场页面bean
	private FarmViewBean farmViewBean;
	//其他农场页面bean
	private OtherFarmViewBean otherFarmViewBean;
	//仓库页面bean
	private RepositoryViewBean repositoryViewBean;
	//商店页面bean
	private ShopViewBean shopViewBean;
	//设置页面bean
	private ConfigViewBean configViewBean; 
	
	/**
	 * 	String LOGINVIEW = "登录页面";
		String REGISTERVIEW = "注册页面";
		String FARMVIEW = "农场页面";
		String OTHERFARMVIEW = "其他农场页面";
		String REPOSITORYVIEW = "仓库页面";
		String SHOPVIEW = "商店页面";
		String CONFIGVIEW = "设置页面";
	 */
	//页面标识字段
	private String pageFlag;

	/**
	 * 
	 * @param loginViewBean
	 * @param pageFlag
	 */
	public Message(LoginViewBean loginViewBean, String pageFlag) {
		this.loginViewBean = loginViewBean;
		this.pageFlag = pageFlag;
	}

	/**
	 * 
	 * @param registerViewBean
	 * @param pageFlag
	 */
	public Message(RegisterViewBean registerViewBean, String pageFlag) {
		this.registerViewBean = registerViewBean;
		this.pageFlag = pageFlag;
	}

	/**
	 * 
	 * @param farmViewBean
	 * @param pageFlag
	 */
	public Message(FarmViewBean farmViewBean, String pageFlag) {
		this.farmViewBean = farmViewBean;
		this.pageFlag = pageFlag;
	}

	/**
	 * 
	 * @param otherFarmViewBean
	 * @param pageFlag
	 */
	public Message(OtherFarmViewBean otherFarmViewBean, String pageFlag) {
		this.otherFarmViewBean = otherFarmViewBean;
		this.pageFlag = pageFlag;
	}

	/**
	 * 
	 * @param repositoryViewBean
	 * @param pageFlag
	 */
	public Message(RepositoryViewBean repositoryViewBean, String pageFlag) {
		this.repositoryViewBean = repositoryViewBean;
		this.pageFlag = pageFlag;
	}

	/**
	 * 
	 * @param shopViewBean
	 * @param pageFlag
	 */
	public Message(ShopViewBean shopViewBean, String pageFlag) {
		this.shopViewBean = shopViewBean;
		this.pageFlag = pageFlag;
	}

	/**
	 * 
	 * @param configViewBean
	 * @param pageFlag
	 */
	public Message(ConfigViewBean configViewBean, String pageFlag) {
		this.configViewBean = configViewBean;
		this.pageFlag = pageFlag;
	}

	
	public LoginViewBean getLoginViewBean() {
		return loginViewBean;
	}

	public void setLoginViewBean(LoginViewBean loginViewBean) {
		this.loginViewBean = loginViewBean;
	}

	public RegisterViewBean getRegisterViewBean() {
		return registerViewBean;
	}

	public void setRegisterViewBean(RegisterViewBean registerViewBean) {
		this.registerViewBean = registerViewBean;
	}

	public FarmViewBean getFarmViewBean() {
		return farmViewBean;
	}

	public void setFarmViewBean(FarmViewBean farmViewBean) {
		this.farmViewBean = farmViewBean;
	}

	public OtherFarmViewBean getOtherFarmViewBean() {
		return otherFarmViewBean;
	}

	public void setOtherFarmViewBean(OtherFarmViewBean otherFarmViewBean) {
		this.otherFarmViewBean = otherFarmViewBean;
	}

	public RepositoryViewBean getRepositoryViewBean() {
		return repositoryViewBean;
	}

	public void setRepositoryViewBean(RepositoryViewBean repositoryViewBean) {
		this.repositoryViewBean = repositoryViewBean;
	}

	public ShopViewBean getShopViewBean() {
		return shopViewBean;
	}

	public void setShopViewBean(ShopViewBean shopViewBean) {
		this.shopViewBean = shopViewBean;
	}

	public ConfigViewBean getConfigViewBean() {
		return configViewBean;
	}

	public void setConfigViewBean(ConfigViewBean configViewBean) {
		this.configViewBean = configViewBean;
	}

	public String getPageFlag() {
		return pageFlag;
	}

	public void setPageFlag(String pageFlag) {
		this.pageFlag = pageFlag;
	}
	
}
