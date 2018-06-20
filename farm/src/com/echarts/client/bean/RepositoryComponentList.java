package com.echarts.client.bean;

import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * 仓库界面中显示物品列表所要用到的组件类
 */
public class RepositoryComponentList implements Serializable{
	private JLabel itemID;// 物品的id
	private JLabel itemName;// 物品的名称
	private JLabel itemType;// 物品的类型
	private JLabel itemInfo;// 物品的简介
	private JLabel itemNumber;// 物品的持有数
	private JLabel itemPrice;// 物品的售价
	private JButton useButton;// 使用按钮
	private JButton sellButton;// 出售按钮

	// 构造方法：实例化各个组件
	public RepositoryComponentList() {
		itemID = new JLabel();
		itemName = new JLabel();
		itemType = new JLabel();
		itemInfo = new JLabel();
		itemNumber = new JLabel();
		itemPrice = new JLabel();
		useButton = new JButton();
		sellButton = new JButton();
	}

	// 由于组件只需要进行一次初始化，所以该类不需要setter方法
	public JLabel getItemID() {
		return itemID;
	}

	public JLabel getItemName() {
		return itemName;
	}

	public JLabel getItemType() {
		return itemType;
	}

	public JLabel getItemInfo() {
		return itemInfo;
	}

	public JLabel getItemNumber() {
		return itemNumber;
	}

	public JLabel getItemPrice() {
		return itemPrice;
	}

	public JButton getUseButton() {
		return useButton;
	}

	public JButton getSellButton() {
		return sellButton;
	}

}
