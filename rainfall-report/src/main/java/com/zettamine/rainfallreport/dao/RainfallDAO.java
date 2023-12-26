package com.zettamine.rainfallreport.dao;

import java.util.List;

import com.zettamine.rainfallreport.entity.AnnualRainfall;

public interface RainfallDAO {
	
	boolean saveAnnualRainfall(AnnualRainfall annualRainfall);
	
	List<AnnualRainfall> getMaxAnnualRainfall();

}
