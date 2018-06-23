package com.echarts.client.bean;

import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * �ֿ��������ʾ��Ʒ�б���Ҫ�õ��������
 */
public class RepositoryComponentList implements Serializable{
	private JLabel itemID;// ��Ʒ��id
	private JLabel itemName;// ��Ʒ������
	private JLabel itemType;// ��Ʒ������
	private JLabel itemInfo;// ��Ʒ�ļ��
	private JLabel itemNumber;// ��Ʒ�ĳ�����
	private JLabel itemPrice;// ��Ʒ���ۼ�
	private JButton useButton;// ʹ�ð�ť
	private JButton sellButton;// ���۰�ť
	private int resGoodsID;//��Ʒ�ڹ�������������id
	private int resID;//�ֿ�id

	// ���췽����ʵ�����������
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

	// �������ֻ��Ҫ����һ�γ�ʼ�������Ը��಻��Ҫsetter����
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

	public int getResGoodsID() {
		return resGoodsID;
	}

	public void setResGoodsID(int resGoodsID) {
		this.resGoodsID = resGoodsID;
	}

	public int getResID() {
		return resID;
	}

	public void setResID(int resID) {
		this.resID = resID;
	}

}
