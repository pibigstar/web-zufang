package com.pibigstar.zufang.utils;

import java.util.ArrayList;
import java.util.List;

import com.pibigstar.zufang.domain.RentHouse;

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
		System.out.println("面积                租金             距离");
		for (int i = 0; i < 10; i++) {
			RentHouse house = subList.get(i);
			
			System.out.print(String.format("%.4f",house.getW1())+"   |");
			System.out.print(String.format("%.4f",house.getW2())+"   |");
			System.out.print(String.format("%.4f",house.getW3()));
			
			System.out.println();
		}
	}
	/**
	 * 贴近度排名 L+ L-
	 * @param subList
	 */
	public static void printL(List<RentHouse> subList) {
		subList = clear(subList);
		System.out.println("==== 贴近度排名 ====");
		System.out.println("    L+       L-");
		for (int i = 0; i < 10; i++) {
			RentHouse house = subList.get(i);
			System.out.print("A"+(i+1)+"  "+ format(house.getL1())+"  "+format(house.getL2()));
			System.out.println();
		}
	}
	/**
	 * 贴近度排名 d+ d-
	 * @param subList
	 */
	public static void printD(List<RentHouse> subList) {
		subList = clear(subList);
		System.out.println("==== 贴近度排名 ====");
		System.out.println("*********D+**********D-");
		for (int i = 0; i < 10; i++) {
			RentHouse house = subList.get(i);
			System.out.print("A"+(i+1)+"  "+ format(house.getD1())+"  "+format(house.getD2()));
			System.out.println();
		}
	}
	/**
	 * 到正负理想解的距离 S+ S-
	 * @param subList
	 */
	public static void printDD(List<RentHouse> subList) {
		subList = clear(subList);
		System.out.println("==== 到正负理想解的距离 ====");
		System.out.println("********S+**********S-");
		for (int i = 0; i < 10; i++) {
			RentHouse house = subList.get(i);
			System.out.print("A"+(i+1)+"  "+ format(house.getMaxS())+"  "+format(house.getMinS()));
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
			double s = 0.5;
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
	
	/**
	 * 格式化
	 * @param obj
	 * @return
	 */
	public static String format(double obj) {
		return String.format("%.4f",obj);
	}
	
	public static double test = 0.03;

}
