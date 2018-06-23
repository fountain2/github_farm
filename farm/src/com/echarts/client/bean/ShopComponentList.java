package com.echarts.client.bean;

import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * �̵��������ʾ�����б���Ҫ�õ��������
 */
public class ShopComponentList implements Serializable {
	private JLabel seedID;// ���ӵ�id
	private JLabel seedName;// ���ӵ�����
	private JLabel seedPrice;// ���ӵ��ۼ�
	private JLabel seedInfo;// ���ӵļ��
	private JLabel seedGetNumber;// ���ӵ��ճ���
	private JLabel seedGetTime;// �����ճɵ�����ʱ��
	private JLabel itemNumber;// �õ��ߵĳ�����
	private JButton buyButton;// ����ť

	// ���췽����ʵ�����������
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

	// �������ֻ��Ҫ����һ�γ�ʼ�������Ը��಻��Ҫsetter����
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
