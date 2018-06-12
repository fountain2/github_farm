package com.echarts.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class Dbutil {
	static List<String> nodelist = null;
	static{
		try {
			Document document = XMLManager.getDocument("src\\com\\echarts\\util\\db.xml");
			nodelist = XMLManager.getChildElementContent(document);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConn2() throws ClassNotFoundException, SQLException{
		String driverName=nodelist.get(0);
		String url=nodelist.get(1);
		String user=nodelist.get(2);
		String password=nodelist.get(3);
		Class.forName(driverName);
		Connection conn = DriverManager.getConnection(url,user,password);
		DatabaseMetaData metaData= conn.getMetaData();
		System.out.println(metaData.getDatabaseProductVersion()+","+metaData.getDatabaseProductName());
		return conn;
	}
	
	public static void closeAll(ResultSet rs,Statement stmt,Connection conn){
		if (rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		
		if (stmt!=null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		if (conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
