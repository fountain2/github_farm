package com.echarts.server.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.echarts.client.bean.Repository;
import com.echarts.client.bean.User;
import com.echarts.util.Dbutil;

public class RepositoryDaoImpl implements RepositoryDao {
	public static Connection conn;

	static {
		try {
			conn = Dbutil.getConn2();
			conn.setAutoCommit(false);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 获取用户信息的方法
	 * @param userID
	 * @return
	 */
	public User checkUser(int userID){
		User user = new User();
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement("select * from user where user_id = ?");
			pstmt.setInt(1, userID);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			user.setUserName(rs.getString("user_name"));
			user.setUserMoney(rs.getInt("user_money"));
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	
	/**
	 * 显示仓库的方法，连接数据库仓库表，获取信息。
	 * 
	 * @param userId
	 *            用户id
	 * @param page
	 *            查看的页码
	 * @return 返回对应的仓库表
	 * @throws SQLException
	 */
	public Repository[] showRepository(int userId, int page) throws SQLException {
		Repository[] repositorys = new Repository[10];
		// 获取对应页码仓库的信息
		PreparedStatement pstmt = conn.prepareStatement("select * from repository where user_id = ?");
		PreparedStatement pstmtPrice;
		PreparedStatement pstmtUser;
		pstmt.setInt(1, userId);
		ResultSet rs = pstmt.executeQuery();
		// i控制查看表，j控制导入仓库数组
		int i = 0;
		int j = 0;

		System.out.println("dao层启动");
		while (rs.next() && i < page * 10) {
			// 从指定页面开始
			if (i >= (page - 1) * 10) {
				System.out.println("Dao" + i);
				Repository repository = new Repository();
				repository.setResId(rs.getInt("res_id"));
				repository.setUserId(rs.getInt("user_id"));
				repository.setResGoodsId(rs.getInt("res_goods_id"));
				repository.setResType(rs.getInt("res_type"));
				repository.setResNumber(rs.getInt("res_number"));
				// 获取种子表和谷物表的内容
				if (rs.getInt("res_type") == 1) {
					System.out.println("crop");
					pstmtPrice = conn.prepareStatement("select * from crop where crop_id = ?");
					pstmtPrice.setInt(1, repository.getResGoodsId());
					ResultSet rsPrice = pstmtPrice.executeQuery();
					rsPrice.next();
					repository.setResGoodsName(rsPrice.getString("crop_name"));
					System.out.println(rsPrice.getString("crop_name"));
					repository.setResPrice(rsPrice.getInt("crop_price"));
					repository.setResBrief(rsPrice.getString("crop_introduction"));
					System.out.println(rsPrice.getInt("crop_price") + "crop_price");
				} else if (rs.getInt("res_type") == 2) {
					pstmtPrice = conn.prepareStatement("select * from seed where seed_id = ?");
					pstmtPrice.setInt(1, repository.getResGoodsId());
					ResultSet rsPrice = pstmtPrice.executeQuery();
					rsPrice.next();
					repository.setResGoodsName(rsPrice.getString("seed_name"));
					repository.setResPrice((int) (rsPrice.getInt("seed_price") * 0.4));
					repository.setResBrief(rsPrice.getString("seed_introduction"));
					System.out.println(rsPrice.getInt("seed_price") + "seed_price");
				}

				// 获取用户表的内容
				pstmtUser = conn.prepareStatement("select * from user where user_id = ?");
				pstmtUser.setInt(1, userId);
				ResultSet rsUser = pstmtUser.executeQuery();
				rsUser.next();
				repository.setUserName(rsUser.getString("user_name"));
				repository.setUserMoney(rsUser.getInt("user_money"));
				repositorys[j] = repository;
				System.out.println(rsUser.getInt("user_money") + "user_money");
				j++;
			}
			i++;
			System.out.println("Dao" + i + "   " + j);
		}
		System.out.println("DaoDao");
		conn.commit();
		return repositorys;
	}

	/**
	 * 查看土地剩余数方法
	 * 
	 * @param userID
	 * @return
	 * @throws SQLException
	 */
	public int checkField(int userID) throws SQLException {
		int fieldRemaining = 0;
		PreparedStatement pstmt = conn.prepareStatement("select * from field where user_id = ?");
		pstmt.setInt(1, userID);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			if (rs.getInt("field_status") == 0) {
				fieldRemaining++;
			}
		}
		conn.commit();
		return fieldRemaining;
	}

	/**
	 * 使用种子的方法
	 * 
	 * @param userID
	 *            用户的id
	 * @param resID
	 *            仓库id
	 * @param resGoodsID
	 *            种子的id
	 * @param useNumber
	 *            使用种子的个数
	 * @return
	 * @throws SQLException
	 */
	public boolean useSeed(int userID, int resID, int resGoodsID, int useNumber) throws SQLException {
		try {
			// 连接土地表
			PreparedStatement pstmt = conn.prepareStatement("select * from field where user_id = ?");
			pstmt.setInt(1, userID);
			ResultSet rs = pstmt.executeQuery();
			// 连接种子表
			PreparedStatement pstmtSeed = conn.prepareStatement("select * from seed where seed_id = ?");
			pstmtSeed.setInt(1, resGoodsID);
			ResultSet rsSeed = pstmtSeed.executeQuery();
			rsSeed.next();
			System.out.println("到这");
			PreparedStatement pstmtRes = conn.prepareStatement("select * from repository where res_id=?");
			pstmtRes.setInt(1, resID);
			ResultSet rsRes = pstmtRes.executeQuery();
			rsRes.next();
			if ((rsRes.getInt("res_number") - useNumber) > 0) {
				System.out.println("减种子");
				PreparedStatement pstmtResChange = conn
						.prepareStatement("update repository set res_number=? where res_id=?");
				pstmtResChange.setInt(1, rsRes.getInt("res_number") - useNumber);
				pstmtResChange.setInt(2, resID);
				pstmtResChange.executeUpdate();
			} else if ((rsRes.getInt("res_number") - useNumber) == 0) {
				System.out.println("删种子");
				PreparedStatement pstmtResChange = conn.prepareStatement("delete from repository where res_id = ?");
				pstmtResChange.setInt(1, resID);
				pstmtResChange.executeUpdate();

			}

			int harvestNumber = rsSeed.getInt("seed_harvest_number");
			System.out.println("丰收" + harvestNumber);
			while (rs.next() && useNumber > 0) {
				if (rs.getInt("field_status") == 0) {
					System.out.println("到这");
					PreparedStatement pstmtChange = conn.prepareStatement(
							"update field set seed_id=?,field_status=-1,filed_harvest_number = ?,field_planting_time=null where field_id=?");
					pstmtChange.setInt(1, resGoodsID);
					pstmtChange.setInt(2, harvestNumber);
					pstmtChange.setInt(3, rs.getInt("field_id"));
					pstmtChange.executeUpdate();
					useNumber--;
				}
			}
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			System.out.println("sql出错");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 售出货物方法
	 * @param userID 用户的id
	 * @param resID 仓库的id
	 * @param sellNumber 出售的货物数
	 * @param resPrice 货物的出售价格
	 * @return
	 */
	public int sellGoods(int userID, int resID, int sellNumber, int resPrice) {
		int sellMoney = sellNumber * resPrice;
		try {
			// 连接仓库表
			PreparedStatement pstmt = conn.prepareStatement("select * from repository where res_id = ?");
			pstmt.setInt(1, resID);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			// 连接用户表，获取用户目前的金币数
			PreparedStatement pstmtUserMoney = conn.prepareStatement("select * from user where user_id = ?");
			pstmtUserMoney.setInt(1, userID);
			ResultSet rsUserMoney = pstmtUserMoney.executeQuery();
			rsUserMoney.next();
			int userMoney = rsUserMoney.getInt("user_money");
			// 连接用户表，修改用户的金币数
			PreparedStatement pstmtUser = conn.prepareStatement("update user set user_money=? where user_id = ?");
			pstmtUser.setInt(1, sellMoney + userMoney);
			pstmtUser.setInt(2, userID);
			pstmtUser.executeUpdate();

			// 判断有没有卖完
			if (rs.getInt("res_number") - sellNumber == 0) {
				// 卖完了，删除对应的仓库表项
				PreparedStatement pstmtSell = conn.prepareStatement("delete from repository where res_id = ?");
				pstmtSell.setInt(1, resID);
				pstmtSell.executeUpdate();
			} else {
				// 没卖完，更新对应的仓库表项
				PreparedStatement pstmtSell = conn
						.prepareStatement("update repository set res_number=? where res_id = ?");
				pstmtSell.setInt(1, rs.getInt("res_number") - sellNumber);
				pstmtSell.setInt(2, resID);
				pstmtSell.executeUpdate();
			}
			conn.commit();

		} catch (SQLException e) {
			try {
				conn.rollback();
				return -1;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return sellMoney;
	}
}
