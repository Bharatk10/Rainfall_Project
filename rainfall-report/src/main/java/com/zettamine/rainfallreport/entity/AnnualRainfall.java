package com.zettamine.rainfallreport.entity;

import java.util.Arrays;
import java.util.OptionalDouble;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnnualRainfall {
	
	private int cityPincode;
	private String cityname;
	private Double averageAnnualRainfall;
	
	
	public static  double calculateAverageAnnualRainfall(double[] montlyRainfall) {
		
		OptionalDouble average = Arrays.stream(montlyRainfall).average();
		
		if(average.isPresent()) {
			return average.getAsDouble();
		}
		
		 return 0;
		
	}


	@Override
	public String toString() {
		return String.format("%-12d |%-10s |%.2f",cityPincode,cityname,averageAnnualRainfall);
	}
	

}
