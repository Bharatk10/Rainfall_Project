package com.zettamine.rainfallreport.service;

import java.util.List;

import com.zettamine.rainfallreport.entity.AnnualRainfall;
import com.zettamine.rainfallreport.exception.InvalidCityPincodeException;

public interface RainfallService {
	
	void saveAnnualRainfall(List<AnnualRainfall> annualRainfall);
	
	List<AnnualRainfall> generateData() throws InvalidCityPincodeException;
	
	List<AnnualRainfall> maxRainfall();
	
	

}
