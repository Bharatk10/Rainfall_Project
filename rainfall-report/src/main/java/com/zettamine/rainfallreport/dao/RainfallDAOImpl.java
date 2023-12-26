package com.zettamine.rainfallreport.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.zettamine.rainfallreport.config.ConnectionFactory;
import com.zettamine.rainfallreport.entity.AnnualRainfall;

public class RainfallDAOImpl implements RainfallDAO {
	
	
	static PreparedStatement stmt = null;
	
	static Connection connection = null;
	
	static {
		
		connection = ConnectionFactory.getDBConnection();
	}
	
	@Override
	public boolean saveAnnualRainfall(AnnualRainfall annualRainfall) {
		
		try {
			stmt = connection.prepareStatement("insert into AnnualRainfall values(?,?,?)");
			
			stmt.setInt(1,annualRainfall.getCityPincode());
			stmt.setString(2,annualRainfall.getCityname());
			stmt.setDouble(3,annualRainfall.getAverageAnnualRainfall());
			stmt.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			
			//System.out.println("Data already Preesent");
			System.out.println(e.getMessage());
			return false;
		}
		
		
	}

	@Override
	public List<AnnualRainfall> getMaxAnnualRainfall() {
		
		List<AnnualRainfall> maxRainFall = new ArrayList<>();
		
		String query = "select * from AnnualRainfall "
				+ "where average_annual_rainfall in "
				+ "(select max(average_annual_rainfall) from AnnualRainfall)";
		
		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				
				maxRainFall.add(new AnnualRainfall(rs.getInt(1),rs.getString(2),rs.getDouble(3)));	
			}
			return maxRainFall;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return null;
	}

}
