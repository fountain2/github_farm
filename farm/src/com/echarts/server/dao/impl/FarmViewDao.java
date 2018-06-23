package com.echarts.server.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.echarts.client.bean.Field;
import com.echarts.client.bean.Repository;
import com.echarts.client.bean.Seed;
import com.echarts.client.bean.User;
import com.echarts.util.Dbutil;


public class FarmViewDao {
	
	private static Connection conn2;
	static{
		try {
			conn2 = Dbutil.getConn2();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据 id 查询用户信息
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	public User queryUserInfoById(int userId ) throws SQLException{
		PreparedStatement pstmt = conn2.prepareStatement("SELECT * FROM USER WHERE user_id = ?");
		pstmt.setInt(1, userId);
		ResultSet rs = pstmt.executeQuery();
		User user =null;
		if (rs.next()) {
			String userName = rs.getString(2);
			String userPaswword = rs.getString(3);
			int userLv = rs.getInt(4);
			int userMoney = rs.getInt(5);
			int userType = rs.getInt(6);
			long time = rs.getTimestamp(7).getTime();
			Date userAccesstime = new Date(time);
			user =new User(userId, userName, userPaswword, userLv, userMoney, userType, userAccesstime);
		}
		return user;
	}

	public List<Field> queryFieldInfoById(int userId) throws SQLException {
		PreparedStatement pstmt = conn2.prepareStatement("SELECT * FROM FIELD WHERE user_id = ?");
		pstmt.setInt(1, userId);
		ResultSet rs = pstmt.executeQuery();
		List<Field> fieldList = new ArrayList<>();
		Field field =null;
		while (rs.next()) {
			int fieldId =rs.getInt(1);
			int seedId = rs.getInt(3);
			int fieldStatus = rs.getInt(4);
			int filedHarvestNumber = rs.getInt(5);
			long time = rs.getTimestamp(6).getTime();
			Date fieldPlantingTime = new Date(time);
			field = new Field(fieldId, userId, seedId, fieldStatus, filedHarvestNumber, fieldPlantingTime);
			fieldList.add(field);
		}
		return fieldList;
	}

	public Seed querySeedInfoById(int seedId) throws SQLException {
		
		PreparedStatement pstmt = conn2.prepareStatement("SELECT * FROM SEED WHERE seed_id = ?");
		pstmt.setInt(1, seedId);
		ResultSet rs = pstmt.executeQuery();
		Seed seed =null;
		if (rs.next()) {
			String seedName = rs.getString(2);
			int seedPrice = rs.getInt(3);
			int seedHarvestNumber = rs.getInt(5);
			int seedHarvestTime = rs.getInt(6);
			int cropId = rs.getInt(7);
			String seedIntroduction = rs.getString(4);
			seed = new Seed(seedId, seedName, seedPrice, seedHarvestNumber, seedHarvestTime, cropId, seedIntroduction);
		}
		return seed;
	}

	public void updateFieldStatus(Integer userId, Integer fieldId, Integer field_toStatus) throws SQLException {
		PreparedStatement pstmt = conn2.prepareStatement("UPDATE FIELD SET field_status = ? WHERE user_id = ? AND field_id = ?");
		pstmt.setInt(1, field_toStatus);
		pstmt.setInt(2, userId);
		pstmt.setInt(3, fieldId);
		pstmt.executeUpdate();
	}

	public Repository queryRepository(Integer userId, Integer resType, Integer resGoodsId) throws SQLException {
		PreparedStatement pstmt = conn2.prepareStatement("SELECT * FROM  repository  WHERE user_id = ? AND res_type =? AND res_goods_id =?");
		pstmt.setInt(1, userId);
		pstmt.setInt(2, resType);
		pstmt.setInt(3, resGoodsId);
		ResultSet rs = pstmt.executeQuery();
		Repository repos =null;
		if (rs.next()) {
			int resId = rs.getInt(1);
			int resNumber = rs.getInt(5);
			repos = new Repository(resId, userId, resGoodsId, resType, resNumber);
		}
		return repos;
	}

	public void insertRepository(Integer userId, Integer resGoodsId, Integer resType, Integer resNumber) throws SQLException {
		PreparedStatement pstmt = conn2.prepareStatement("INSERT INTO repository (user_id, res_goods_id, res_type, res_number) VALUES(?,?,?,?)");
		pstmt.setInt(1, userId);
		pstmt.setInt(2, resGoodsId);
		pstmt.setInt(3, resType);
		pstmt.setInt(4, resNumber);
		pstmt.executeUpdate();
	}

	public void updateRepository(Integer userId, Integer resGoodsId, Integer resType, Integer resNumber) throws SQLException {
		PreparedStatement pstmt = conn2.prepareStatement("UPDATE repository SET res_number = ? WHERE user_id = ?  AND res_goods_id =? AND res_type =?");
		pstmt.setInt(1, resNumber);
		pstmt.setInt(2, userId);
		pstmt.setInt(3, resGoodsId);
		pstmt.setInt(4, resType);
		pstmt.executeUpdate();
	}

	public void updateFieldHarvestNumber(Integer userId, Integer fieldId, Integer filedHarvestNumber) throws SQLException {
		PreparedStatement pstmt = conn2.prepareStatement("UPDATE FIELD SET filed_harvest_number = ? WHERE user_id = ? AND field_id = ?");
		pstmt.setInt(1, filedHarvestNumber);
		pstmt.setInt(2, userId);
		pstmt.setInt(3, fieldId);
		pstmt.executeUpdate();
	}

	public List<User> queryUserList() throws SQLException {
		PreparedStatement pstmt = conn2.prepareStatement("SELECT * FROM user");
		ResultSet rs = pstmt.executeQuery();
		List<User> userlist=new ArrayList<>();
		User user=null;
		while (rs.next()) {
			int userId = rs.getInt(1);
			String userName = rs.getString(2);
			String userPaswword = rs.getString(3);
			int userLv = rs.getInt(4);
			int userMoney = rs.getInt(5);
			int userType = rs.getInt(6);
			Date userAccesstime = rs.getTimestamp(7);
			user = new User(userId, userName, userPaswword, userLv, userMoney, userType, userAccesstime);
			userlist.add(user);
		}
		return userlist;
	}
}
