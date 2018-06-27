package com.pibigstar.sort;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.pibigstar.domain.RentHouse;
import com.pibigstar.domain.Variation;

public class SortResult {
	
	public static List<RentHouse> sort(List<RentHouse>houses,Variation variation){
		
		for (RentHouse house : houses) {
			double longitude = Double.parseDouble(house.getLongitude());
			double dimension = Double.parseDouble(house.getDimension());
			double rent = Double.parseDouble(house.getRent());
			double distance = Math.sqrt(Math.pow(longitude-variation.getLongitude(), 2)+Math.pow(dimension-variation.getDimension(), 2));
			
			double weight = distance*variation.getDistance()+ rent*variation.getMoney();
			house.setWeight(weight);
		}
		
		Collections.sort(houses);
		
		return houses;
			
	}

}
