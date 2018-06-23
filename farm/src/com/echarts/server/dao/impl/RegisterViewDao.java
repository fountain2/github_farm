package com.echarts.server.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.echarts.client.bean.User;
import com.echarts.util.Dbutil;

public class RegisterViewDao {
	private static Connection connection;
	static {
		try {
			connection = Dbutil.getConn2();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public User queryUser(String userName) throws SQLException {
		PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM user where user_name = ?");
		pstmt.setString(1, userName);
		ResultSet rs = pstmt.executeQuery();
		User user = null;
		if (rs.next()) {
			String name = rs.getString(2);
			String password = rs.getString(3);
			user =new User();
			user.setUserName(name);
			user.setUserPaswword(password);
		}
		return user;
	}

	public void registerUser(User userRegister) throws SQLException {
		PreparedStatement pstmt = connection.prepareStatement("insert into user values(null,?,?,1,8102,2,null)");
		pstmt.setString(1, userRegister.getUserName());
		pstmt.setString(2, userRegister.getUserPaswword());
		pstmt.executeUpdate();
		
		PreparedStatement pstmt2 = connection.prepareStatement("SELECT user_id FROM USER WHERE user_name =? ");
		pstmt2.setString(1, userRegister.getUserName());
		ResultSet rs = pstmt2.executeQuery();
		int user_id = 0;
		if (rs.next()) {
			user_id=rs.getInt(1);
		}
		
		PreparedStatement pstmt3 = connection.prepareStatement("INSERT INTO FIELD VALUES(NULL,?,1,0,0,NULL)");
		pstmt3.setInt(1, user_id);
		for (int i = 0; i < 4; i++) {
			pstmt3.executeUpdate();
		}
	}
}
