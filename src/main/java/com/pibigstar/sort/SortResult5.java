package com.pibigstar.sort;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.pibigstar.domain.Matrix;
import com.pibigstar.domain.RentHouse;
import com.pibigstar.domain.Variation;
import com.pibigstar.sort.px.GetMatrix;
import com.pibigstar.utils.ZuFangUtil;

/**
 * 马氏距离
 * @author pibigstar
 *
 */
public class SortResult5 {


	public static List<RentHouse> sort(List<RentHouse>houses,Variation variation){

		List<RentHouse> subList = ZuFangUtil.clear(houses);
		//List<RentHouse> subList = houses;
		double minRent = 0 ,minArea = 0,minDistance = 0;
		double SRent = 0 ,SArea = 0,SDistance = 0;

		//寻找出每个属性的最小值
		for (RentHouse house : subList) {
			double longitude = Double.parseDouble(house.getLongitude());
			double dimension = Double.parseDouble(house.getDimension());

			double rent = Double.parseDouble(house.getRent());//租金

			double area = Double.parseDouble(house.getArea().replace("平米", "").trim());//租金

			house.setRent(rent+"");
			house.setArea(area+"");
			double distance = (Math.sqrt(Math.pow(longitude-variation.getLongitude(), 2)+Math.pow(dimension-variation.getDimension(), 2)))*1000;
			house.setDistance(distance);
			/**
			 * 最小值
			 */
			if (minRent==0||minRent>rent) {
				minRent = rent;
			}
			if (minArea==0||minArea>area) {
				minArea = rent;
			}
			if (minDistance==0||minDistance>distance) {
				minDistance = distance;
			}

			/**
			 * 最大值
			 */
			if (SRent==0||SRent<rent) {
				SRent = rent;
			}
			if (SArea==0||minArea<area) {
				minArea = rent;
			}
			if (SDistance==0||SDistance<distance) {
				SDistance = distance;
			}

		}
		DecimalFormat df = new DecimalFormat("#.0000");
		System.out.print("排序前：");
		System.out.println(df.format(ZuFangUtil.criteria(subList)));

		Matrix matrix = GetMatrix.get(subList);

		for (RentHouse house : subList) {

			double rent = Double.parseDouble(house.getRent());//租金
			double area = Double.parseDouble(house.getArea().replace("平米", "").trim());//租金
			double distance = house.getDistance();
			// d +
			double T1 = Math.abs(rent - SRent);
			double T2 = Math.abs(area - SArea);
			double T3 = Math.abs(distance - SDistance);
			
			double d1 = T1*(T1*matrix.c1+T2*matrix.c2+T3*matrix.c3)+T2*(T1*matrix.b1+T2*matrix.b2+T3*matrix.b3)+T3*(T1*matrix.a1+T2*matrix.a2+T3*matrix.a3);
			house.setD1(d1);
			
			// d -
			double t1 = Math.abs(rent - minRent);
			double t2 = Math.abs(area - minArea);
			double t3 = Math.abs(distance - minDistance);
			
			double d2 = t1*(t1*matrix.c1+t2*matrix.c2+t3*matrix.c3)+t2*(t1*matrix.b1+t2*matrix.b2+t3*matrix.b3)+t3*(t1*matrix.a1+t2*matrix.a2+t3*matrix.a3);
			house.setD2(d2);
			
			double c = d1/(d1+d2);
			house.setC(c);
		}
		
		//排序
		Collections.sort(subList);

		System.out.print("排序后：");
		System.out.println(df.format(ZuFangUtil.criteria(subList)));
		
		
		ZuFangUtil.printC(subList);
		
		
		System.out.println("     相关系系数矩阵    ");
		System.out.println("[ "+String.format("%.4f",matrix.a1)+" "+String.format("%.4f",matrix.a2)+" "+String.format("%.4f",matrix.a3)+" ]");		
		System.out.println("[ "+String.format("%.4f",matrix.b1)+" "+String.format("%.4f",matrix.b2)+" "+String.format("%.4f",matrix.b3)+" ]");		
		System.out.println("[ "+String.format("%.4f",matrix.c1)+" "+String.format("%.4f",matrix.c2)+" "+String.format("%.4f",matrix.c3)+" ]");		

		return subList;
	}
}
