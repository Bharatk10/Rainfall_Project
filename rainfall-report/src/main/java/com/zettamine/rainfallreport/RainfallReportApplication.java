package com.zettamine.rainfallreport;

import java.util.List;

import com.zettamine.rainfallreport.entity.AnnualRainfall;
import com.zettamine.rainfallreport.exception.InvalidCityPincodeException;
import com.zettamine.rainfallreport.service.RainfallService;
import com.zettamine.rainfallreport.service.RainfallServiceImpl;

public class RainfallReportApplication {
	
	static RainfallService rainfallservice;
	
	static{
		rainfallservice = new RainfallServiceImpl();
	}

	public static void main(String[] args) throws InvalidCityPincodeException {
		
//		List<AnnualRainfall> rainfallData = rainfallservice.generateData();
//		
//		rainfallservice.saveAnnualRainfall(rainfallData);
		
		List<AnnualRainfall> maxrainfall = rainfallservice.maxRainfall();
		
		if(maxrainfall!=null) {
			System.out.printf("%-12s |%-10s |%s\n","CityPincode","City Name","Average Rainfall");
			for (AnnualRainfall annualRainfall : maxrainfall) {
				System.out.println(annualRainfall);
			}
		}

	}

}
