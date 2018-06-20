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
	//��¼ҳ��bean
	private LoginViewBean loginViewBean; 
	//ע��ҳ��bean
	private RegisterViewBean registerViewBean;
	//ũ��ҳ��bean
	private FarmViewBean farmViewBean;
	//����ũ��ҳ��bean
	private OtherFarmViewBean otherFarmViewBean;
	//�ֿ�ҳ��bean
	private RepositoryViewBean repositoryViewBean;
	//�̵�ҳ��bean
	private ShopViewBean shopViewBean;
	//����ҳ��bean
	private ConfigViewBean configViewBean; 
	
	/**
	 * 	String LOGINVIEW = "��¼ҳ��";
		String REGISTERVIEW = "ע��ҳ��";
		String FARMVIEW = "ũ��ҳ��";
		String OTHERFARMVIEW = "����ũ��ҳ��";
		String REPOSITORYVIEW = "�ֿ�ҳ��";
		String SHOPVIEW = "�̵�ҳ��";
		String CONFIGVIEW = "����ҳ��";
	 */
	//ҳ���ʶ�ֶ�
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
