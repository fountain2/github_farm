package com.echarts.server.dao.impl;

import java.util.List;

import com.echarts.client.bean.Seed;

public interface ShopViewDao {
	
	/**
	 * 获得用户金币的方法：查询用户表获得对应数据
	 */
	int getMoney(int userID);

	/**
	 * 升级农场的方法：操作用户表的农场等级
	 */
	int levelUp(int userID, boolean isWantToBuy);

	/**
	 * 切换商店页面的方法：查询种子表并根据页码获得对应数据
	 */
	List<Seed> getSeedPage(int userID, int page);

	/**
	 * 购买种子（商品）的方法：操作仓库表
	 */
	void buySeed(int userID, int seedID, int numberResult, int moneyResult);
}
