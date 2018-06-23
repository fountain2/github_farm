package com.echarts.client.bean;

import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * 商店界面中显示种子列表所要用到的组件类
 */
public class ShopComponentList implements Serializable {
	private JLabel seedID;// 种子的id
	private JLabel seedName;// 种子的名称
	private JLabel seedPrice;// 种子的售价
	private JLabel seedInfo;// 种子的简介
	private JLabel seedGetNumber;// 种子的收成数
	private JLabel seedGetTime;// 种子收成的所需时间
	private JLabel itemNumber;// 该道具的持有数
	private JButton buyButton;// 购买按钮

	// 构造方法：实例化各个组件
	public ShopComponentList() {
		seedID = new JLabel();
		seedName = new JLabel();
		seedPrice = new JLabel();
		seedInfo = new JLabel();
		seedGetNumber = new JLabel();
		seedGetTime = new JLabel();
		itemNumber = new JLabel();
		buyButton = new JButton();
	}

	// 由于组件只需要进行一次初始化，所以该类不需要setter方法
	public JLabel getSeedID() {
		return seedID;
	}

	public JLabel getSeedName() {
		return seedName;
	}

	public JLabel getSeedPrice() {
		return seedPrice;
	}

	public JLabel getSeedInfo() {
		return seedInfo;
	}

	public JLabel getSeedGetNumber() {
		return seedGetNumber;
	}

	public JLabel getSeedGetTime() {
		return seedGetTime;
	}

	public JLabel getItemNumber() {
		return itemNumber;
	}

	public JButton getBuyButton() {
		return buyButton;
	}

}
