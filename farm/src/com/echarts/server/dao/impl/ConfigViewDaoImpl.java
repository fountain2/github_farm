package com.echarts.server.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.swing.JButton;
import javax.swing.JOptionPane;




import com.echarts.util.Dbutil;





public class ConfigViewDaoImpl implements ConfigViewDao{
	
	private static Connection conn2;
	
	static{
	    try {
			conn2 = Dbutil.getConn2();
			
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	

	//通过userID获得数据库中对应的userPaswword
	public String searchUser(int userID,String oldPassword,String newpassword1) throws SQLException{
		    PreparedStatement state = conn2.prepareStatement("select * from user where user_id = ?");
		    state.setInt(1, userID);
//		    state.setInt(1, 3);
		    ResultSet rs = state.executeQuery();
            rs.next();
            if(rs.getString("user_paswword").equals(oldPassword)){
            	state = conn2.prepareStatement("update user set user_paswword = ? where user_id = ?");
            	state.setString(1, newpassword1);
            	state.setInt(2, userID);
            	state.executeUpdate();
            	String result = "修改密码成功";
            	return result;

            }else {
            	return "旧密码不匹配";
            }
		    
     
	}
}
