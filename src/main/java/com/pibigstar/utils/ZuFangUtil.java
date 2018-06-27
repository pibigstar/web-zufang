package com.pibigstar.utils;

import java.util.ArrayList;
import java.util.List;

import com.pibigstar.domain.RentHouse;

public class ZuFangUtil {

	public static void print(List<RentHouse> subList) {
		subList = clear(subList);
		System.out.println("面积            租金             距离");
		for (int i = 0; i < 10; i++) {
			RentHouse house = subList.get(i);
			
			System.out.print(String.format("%.4f",Double.parseDouble(house.getArea()))+"   |");
			System.out.print(String.format("%.4f",Double.parseDouble(house.getRent()))+"   |");
			System.out.print(String.format("%.4f",house.getDistance()));
			System.out.println();
		}
	}
	/**
	 * 打印归一化后的的数据 q
	 * @param subList
	 */
	public static void printY(List<RentHouse> subList) {
		subList = clear(subList);
		System.out.println("==== 归一化后的的数据 ====");
		System.out.println("面积            租金             距离");
		for (int i = 0; i < 10; i++) {
			RentHouse house = subList.get(i);
			
			System.out.print(String.format("%.4f",house.getY1())+"   |");
			System.out.print(String.format("%.4f",house.getY2())+"   |");
			System.out.print(String.format("%.4f",house.getY3()));
			
			System.out.println();
		}
	}
	/**
	 * 乘以熵之后
	 * @param subList
	 */
	public static void printW(List<RentHouse> subList) {
		subList = clear(subList);
		System.out.println("==== 乘以熵之后 ====");
		System.out.println("面积            租金             距离");
		for (int i = 0; i < 10; i++) {
			RentHouse house = subList.get(i);
			
			System.out.print(String.format("%.4f",house.getW1())+"   |");
			System.out.print(String.format("%.4f",house.getW2())+"   |");
			System.out.print(String.format("%.4f",house.getW3()));
			
			System.out.println();
		}
	}
	
	/**
	 * 打印C
	 * @param subList
	 */
	public static void printC(List<RentHouse> subList) {
		System.out.println("==== C值 ====");
		for (int i = 0; i < 10; i++) {
			RentHouse house = subList.get(i);
			System.out.println(String.format("%.4f",house.getC()));
		}
	}
	

	/**
	 * 评判标准
	 * @param list
	 * @return
	 */
	public static double criteria(List<RentHouse> list) {
		double error = 0;
		for (int i = 0; i < list.size()-1; i++) {
			//面积
			RentHouse r1 = list.get(i);
			RentHouse r2 = list.get(i+1);
			double area1 = Double.parseDouble(r1.getArea().replace("平米", "").trim());//租金
			double area2 = Double.parseDouble(r2.getArea().replace("平米", "").trim());//租金
			double area = area1-area2;
			if (area<0) {
				area = 0;
			}


			//租金
			double rent1 = Double.parseDouble(r1.getRent());
			double rent2 = Double.parseDouble(r2.getRent());
			double rent = rent1-rent2;
			if (rent<0) {
				rent = 0;
			}

			//距离
			double distance1 = r1.getDistance();
			double distance2 = r2.getDistance();
			double distance = distance1-distance2;
			if (distance<0) {
				distance = 0;
			}
			double sum = area+rent+distance;
			error+=sum;
		}
		return error;
	}
	
	
	/**
	 * 数据清洗
	 * @param lists
	 * @return
	 */
	public static List<RentHouse> clear(List<RentHouse> lists){
		List<RentHouse> houses = new ArrayList<RentHouse>();
		int i = 0;
		for (RentHouse rentHouse : lists) {
			if (rentHouse.getArea().length()>6) {
				continue;
			}else {
				houses.add(rentHouse);
				i++;
				if (i>=200) {
					break;
				}
			}
		}
		return houses;
	}
	
	public static double test = 0.03;

}
