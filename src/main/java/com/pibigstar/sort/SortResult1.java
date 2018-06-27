package com.pibigstar.sort;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.pibigstar.domain.RentHouse;
import com.pibigstar.domain.Variation;
import com.pibigstar.utils.ZuFangUtil;

/**
 * 排序1  熵求权重
 * @author pibigstar
 *
 */
public class SortResult1 {
	
	public static List<RentHouse> sort(List<RentHouse>houses,Variation variation){
		
		List<RentHouse> subList = clear(houses);
		//List<RentHouse> subList = houses;
		double minRent = 0 ,minArea = 0,minDistance = 0;
		
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
			
			if (minRent==0||minRent>rent) {
				minRent = rent;
			}
			if (minArea==0||minArea>area) {
				minArea = rent;
			}
			if (minDistance==0||minDistance>distance) {
				minDistance = distance;
			}
			
		}
		DecimalFormat df = new DecimalFormat("#.0000");
		System.out.print("排序前：");
		System.out.println(df.format(ZuFangUtil.criteria(subList)));
		
		
		ZuFangUtil.print(subList);
		
		
		/**
		 * 	q1  面积
		 *	q2  租金
		 *	q3  距离
		 */
		//4-1
		for (RentHouse house : subList) {
			double rent = Double.parseDouble(house.getRent());//租金
			if (house.getArea().length()>6) {
				continue;
			}
			double area = Double.parseDouble(house.getArea().replace("平米", "").trim());//租金
			double distance = house.getDistance();
			
			if (area==0) {
				house.setQ1(1);
			}else {
				house.setQ1(minArea/area);
			}
			if (rent==0) {
				house.setQ2(1);
			}else {
				house.setQ2(minRent/rent);
			}
			if(distance==0) {
				house.setQ3(1);
			}else {
				house.setQ3(minDistance/distance);
			}
		}
		
		double sumQ1 = 0,sumQ2 = 0,sumQ3 = 0;
		
		for (RentHouse house : subList) {
			sumQ1+=Math.pow(house.getQ1(), 2);
			sumQ2+=Math.pow(house.getQ2(), 2);
			sumQ3+=Math.pow(house.getQ3(), 2);
		}
		
		/**
		 * 归一化
		 */
		// 4-2
		for (RentHouse house : subList) {
			double q1 = house.getQ1();
			double q2 = house.getQ2();
			double q3 = house.getQ3();
			
			double y1 = q1/Math.pow(sumQ1, 0.5);
			double y2 = q2/Math.pow(sumQ1, 0.5);
			double y3 = q3/Math.pow(sumQ1, 0.5);
			house.setY1(y1);
			house.setY2(y2);
			house.setY3(y3);
			
		}
		ZuFangUtil.printY(subList);
		
		/**
		 * 熵求权重
		 */
		double P1 = 0, P2 =0, P3 = 0;
		for (RentHouse house2 : subList) {
			double t1 = Math.abs(house2.getY1());
			double t2 = Math.abs(house2.getY2());
			double t3 = Math.abs(house2.getY3());
			P1+=t1;
			P2+=t2;
			P3+=t3;
		}
		
		double E1 = 0,E2 = 0,E3 = 0;
		for (RentHouse house : subList) {
			house.setP1(house.getY1()/P1);
			house.setP2(house.getY1()/P2);
			house.setP3(house.getY1()/P3);
				
			E1+=house.getP1()* Math.log(house.getP1());
			E2+=house.getP2()* Math.log(house.getP2());
			E3+=house.getP3()* Math.log(house.getP3());
		}
		E1 = E1* Math.pow(Math.log(subList.size()),-1);
		E2 = E2* Math.pow(Math.log(subList.size()),-1);
		E3 = E3* Math.pow(Math.log(subList.size()),-1);
		
		double w1 = 0,w2 = 0,w3 = 0;
		
		w1 = E1/(3-E1+E2+E3); // 面积的权值
		w2 = E2/(3-E1+E2+E3); // 租金的权值
		w3 = E3/(3-E1+E2+E3); // 距离的权值
		
		/**
		 * 计算最优解
		 */
		//4-3 
		for (RentHouse house : subList) {
			house.setW1(house.getY1()*w1);
			house.setW2(house.getY1()*w2);
			house.setW3(house.getY1()*w3);
		}
		
		double maxV1 = 0,maxV2 = 0,maxV3 = 0;
		double minV1 = 0,minV2 = 0,minV3 = 0;
		//4-4
		for (RentHouse house : subList) {
			if (maxV1<house.getW1()) {
				maxV1 = house.getW1();
			}
			if (minV1>house.getW1()) {
				minV1 = house.getW1();
			}
			if (maxV2<house.getW2()) {
				maxV2 = house.getW2();
			}
			if (minV2>house.getW2()) {
				minV2 = house.getW2();
			}
			if (maxV3<house.getW3()) {
				maxV1 = house.getW1();
			}
			if (minV3>house.getW3()) {
				minV3 = house.getW3();
			}
		}
		//4-6
		for (RentHouse house : subList) {
			double s1 = Math.pow(house.getW1() - maxV1,2);
			double s2 = Math.pow(house.getW2() - maxV2,2);
			double s3 = Math.pow(house.getW3() - maxV3,2);
			double maxS = s1+s2+s3;
			house.setMaxS(maxS);
			
			s1 = Math.pow(house.getW1()-minV1, 2);
			s2 = Math.pow(house.getW1()-minV2, 2);
			s3 = Math.pow(house.getW1()-minV3, 2);
			double minS = s1+s2+s3;
			house.setMinS(minS);
		}
		//4-8
		for (RentHouse house : subList) {
			double c = house.getMinS()/(house.getMaxS()+house.getMinS());
			house.setC(c);
		}
		
		Collections.sort(subList);
		
		System.out.print("排序后：");
		System.out.println(df.format(ZuFangUtil.criteria(subList)));
		return subList;
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
}
