package com.echarts.server.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.echarts.client.bean.Seed;
import com.echarts.util.Dbutil;

public class ShopViewDaoImpl implements ShopViewDao {

	private static Connection conn;
	// 调用连接数据库的工具类
	static {
		try {
			conn = Dbutil.getConn2();
			conn.setAutoCommit(false);//设置为手动提交事务
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获得用户金币数的方法
	 */
	@Override
	public int getMoney(int userID) {
		int userMoney = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement("select * from user where user_id = ?");
			pstmt.setInt(1, userID);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			userMoney = rs.getInt("user_money");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userMoney;
	}

	/**
	 * 升级农场等级的方法
	 */
	@Override
	public int levelUp(int userID, boolean isWantToBuy) {
		int userLV = 0;
		int userMoney = 0;

		// 获取当前农场等级和用户的金钱数
		try {
			PreparedStatement pstmt = conn.prepareStatement("select * from user where user_id = ?");
			pstmt.setInt(1, userID);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			userLV = rs.getInt("user_lv");
			userMoney = rs.getInt("user_money");
			conn.commit();//手动提交事务
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 处理当前农场等级和升级所需费用，以及等级提升带来的土地数扩展
		// 同时判断用户是处于“查看”阶段，还是“购买”阶段
		// “查看”：看升级农场的相关信息，决定是否升级农场的阶段
		// “购买”：确定了购买农场升级的阶段
		if (userLV < 6) {
			int needMoney = userLV * userLV * 1000;// 计算升级所需的费用
			if (isWantToBuy) {
				if (needMoney <= userMoney) {// 判断用户持有的金币是否足够
					userMoney -= needMoney;
					try {
						conn.setAutoCommit(false);//设置为手动提交事务
						// 操作数据库，对用户的金币和等级进行处理
						PreparedStatement pstmt = conn
								.prepareStatement("update user set user_lv =?,user_money =? where user_id =?");
						pstmt.setInt(1, userLV + 1);
						pstmt.setInt(2, userMoney);
						pstmt.setInt(3, userID);
						pstmt.executeUpdate();
						// 操作数据库，对用户的土地数进行处理
						pstmt = conn.prepareStatement("insert into field value(null,?,?,?,?,null)");
						pstmt.setInt(1, userID);
						pstmt.setInt(2, 1);
						pstmt.setInt(3, 0);
						pstmt.setInt(4, 0);
						switch (userLV) {
						case 1:
						case 2:
						case 3:
						case 4:
							pstmt.executeUpdate();
							pstmt.executeUpdate();
							break;
						case 5:
							pstmt.executeUpdate();
							pstmt.executeUpdate();
							pstmt.executeUpdate();
							break;
						}
						conn.commit();//手动提交事务
					} catch (SQLException e) {
						try {
							conn.rollback();//SQL语句出错时回滚事务
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
					return userMoney;// 购买后，返回用户金额余量
				} else {
					return -2;// 表示不够金币购买
				}
			} else {
				return needMoney;// “查看”状态时返回所需的花费给界面
			}
		} else {
			return -1;// 表示农场等级达到上限
		}
	}

	/**
	 * 获取新页码的种子列表（1~10个记录）
	 */
	@Override
	public List<Seed> getSeedPage(int userID, int page) {
		List<Seed> seedList = new ArrayList<Seed>();// 储存新页码的种子信息
		int pageStart = (page - 1) * 10 + 1;// 新页码的起始条目数
		int pageEnd = page * 10;// 新页码的终止条目数
		int i = 1;// 记录当前条目数
		try {
			// 查找种子表的所有条目，取其中对应当前页码数的<=10条
			PreparedStatement pstmt = conn.prepareStatement("select * from seed");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				if (i < pageStart) {// 小于起始条目数的条目直接跳过
					i++;
					continue;
				} else if (i <= pageEnd) {// 仅对满足条目数范围的条目进行处理
					i++;
					// 查询当前的种子的相关信息
					Seed seed = new Seed();
					seed.setSeedId(rs.getInt("seed_id"));
					seed.setSeedName(rs.getString("seed_name"));
					seed.setSeedPrice(rs.getInt("seed_price"));
					seed.setSeedIntroduction(rs.getString("seed_introduction"));
					seed.setSeedHarvestNumber(rs.getInt("seed_harvest_number"));
					seed.setSeedHarvestTime(rs.getInt("seed_harvest_time"));
					seed.setCropId(rs.getInt("crop_id"));
					// 查询当前的种子对应的持有数
					PreparedStatement pstmt1 = conn.prepareStatement(
							"select * from repository where user_id =? and res_type =2 and res_goods_id=?");
					pstmt1.setInt(1, userID);
					pstmt1.setInt(2, rs.getInt("seed_id"));
					ResultSet rs1 = pstmt1.executeQuery();
					if (rs1.next()) {
						seed.setSeedNumber(rs1.getInt("res_number"));
					} else {
						seed.setSeedNumber(0);
					}
					// 把当前的种子条目加入集合
					seedList.add(seed);
				}
			}
			conn.commit();//手动提交事务
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return seedList;// 返回获得的种子条目
	}

	/**
	 * 购买种子的方法
	 */
	@Override
	public void buySeed(int userID, int seedID, int numberResult, int moneyResult) {
		// numberResult为购买成功时预计的持有量，moneyResult为购买成功时预计剩余的钱
		try {
			// 处理用户的剩余金币
			PreparedStatement pstmt = conn.prepareStatement("update user set user_money=? where user_id =?");
			pstmt.setInt(1, moneyResult);
			pstmt.setInt(2, userID);
			pstmt.executeUpdate();
			// 添加种子到用户的仓库中
			// 判断种子是否存在
			pstmt = conn
					.prepareStatement("select * from repository where user_id =? and res_type = 2 and res_goods_id =?");
			pstmt.setInt(1, userID);
			pstmt.setInt(2, seedID);
			ResultSet rs = pstmt.executeQuery();
			if (!rs.next()) {// 不存在时插入新的相关记录
				pstmt = conn.prepareStatement("insert into repository value(null,?,?,2,?)");
				pstmt.setInt(1, userID);
				pstmt.setInt(2, seedID);
				pstmt.setInt(3, numberResult);
				pstmt.executeUpdate();
			} else {// 存在时更新相关记录
				pstmt = conn.prepareStatement(
						"update repository set res_number =? where user_id =? and res_type =2 and res_goods_id =?");
				pstmt.setInt(1, numberResult);
				pstmt.setInt(2, userID);
				pstmt.setInt(3, seedID);
				pstmt.executeUpdate();
			}
			conn.commit();//处理完成后手动提交事务
		} catch (SQLException e) {
			try {
				conn.rollback();//SQL语句出错时回滚事务
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
