package com.echarts.server.dao.impl;

import java.util.List;

import com.echarts.client.bean.Seed;

public interface ShopViewDao {
	
	/**
	 * ����û���ҵķ�������ѯ�û����ö�Ӧ����
	 */
	int getMoney(int userID);

	/**
	 * ����ũ���ķ����������û����ũ���ȼ�
	 */
	int levelUp(int userID, boolean isWantToBuy);

	/**
	 * �л��̵�ҳ��ķ�������ѯ���ӱ�����ҳ���ö�Ӧ����
	 */
	List<Seed> getSeedPage(int userID, int page);

	/**
	 * �������ӣ���Ʒ���ķ����������ֿ��
	 */
	void buySeed(int userID, int seedID, int numberResult, int moneyResult);
}
