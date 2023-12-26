package com.zettamine.rainfallreport.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionFactory {
	
public static Connection con = null;
	
	public static Connection getDBConnection(){
		
		ResourceBundle resource = ResourceBundle.getBundle("dbconfig");
		
		String className = resource.getString("driverClass");
		String url = resource.getString("url");
		String username = resource.getString("username");
		String password = resource.getString("password");
		
		try {
			Class.forName(className);
			con = DriverManager.getConnection(url,username,password);
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		
		return con;
	}



}
