package com.echarts.server.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.echarts.client.bean.User;
import com.echarts.util.Dbutil;

public class LoginViewDao {
	private static Connection connection;
	static {
		try {
			connection = Dbutil.getConn2();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public User login(User user) throws SQLException {
		String userName = user.getUserName();
		String password = user.getUserPaswword();
		PreparedStatement pstmt = connection
				.prepareStatement("SELECT * FROM user where user_name = ? AND user_paswword =?");
		pstmt.setString(1, userName);
		pstmt.setString(2, password);
		ResultSet rs = pstmt.executeQuery();
		User userBack = null;
		if (rs.next()) {
			int userId = rs.getInt(1);
			String name = rs.getString(2);
			String password2 = rs.getString(3);
			userBack = new User();
			userBack.setUserId(userId);
			userBack.setUserName(name);
			userBack.setUserPaswword(password2);
		}
		return userBack;
	}
}
