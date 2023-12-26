package com.zettamine.rainfallreport.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.zettamine.rainfallreport.dao.RainfallDAO;
import com.zettamine.rainfallreport.dao.RainfallDAOImpl;
import com.zettamine.rainfallreport.entity.AnnualRainfall;
import com.zettamine.rainfallreport.exception.InvalidCityPincodeException;

public class RainfallServiceImpl implements RainfallService {
	
	static RainfallDAO rainfallrepo;
	
	static {
		rainfallrepo = new RainfallDAOImpl();
		
	}

	@Override
	public void saveAnnualRainfall(List<AnnualRainfall> annualRainfall) {
		
		for (AnnualRainfall rainfall : annualRainfall) {
			
			Boolean status = rainfallrepo.saveAnnualRainfall(rainfall);
			if(status == false) {
				System.out.println("The Data not inserted");
				System.exit(0);
			}	
		}
		System.out.println("The data inserted");
	}
		
	@Override
	public List<AnnualRainfall> generateData() throws InvalidCityPincodeException {
		
		List<AnnualRainfall> annualRainfallList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Bharat Kumar\\Desktop\\RainfallData.txt"))) {
            String line;

            while ((line = br.readLine()) != null) {
               
                AnnualRainfall annualRainfall = processLine(line);
             
                if (annualRainfall != null) {
                    annualRainfallList.add(annualRainfall);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();  
        }

        return annualRainfallList;

	}
	private static AnnualRainfall processLine(String line) throws InvalidCityPincodeException {
        
		String[] data = line.split(",");
        
        try {
        	try {
        		String pincode = data[0];
        		if(pincode.length()!=5)
        			throw new InvalidCityPincodeException("It is invalid pincode:"+data[0]+"\nPlease enter valid pincode.");
        	}
        	catch(InvalidCityPincodeException ex) {
        		System.out.println(ex.getMessage());
        		return null;
        	}
        	
        	
        	
            int cityPincode = Integer.parseInt(data[0].trim());
            
            
            String cityName = data[1].trim();
            double[] monthlyRainfall = new double[12];

            for (int i = 2; i < data.length; i++) {
                monthlyRainfall[i - 2] = Double.parseDouble(data[i].trim());
            }
            
            double average = AnnualRainfall.calculateAverageAnnualRainfall(monthlyRainfall);
            
            AnnualRainfall annualRainfall = new AnnualRainfall(cityPincode, cityName,average );
            return annualRainfall;

        } catch (NumberFormatException e) {
            System.out.println("Invalid number format in line: " + line);
            return null;
        }

	}
	
	@Override
	public List<AnnualRainfall> maxRainfall() {
		
		List<AnnualRainfall> maxRainfall = rainfallrepo.getMaxAnnualRainfall();
		
		return maxRainfall;
	}
	
}