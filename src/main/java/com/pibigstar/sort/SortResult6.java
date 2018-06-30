package com.pibigstar.sort;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.pibigstar.domain.Matrix;
import com.pibigstar.domain.RentHouse;
import com.pibigstar.domain.Variation;
import com.pibigstar.sort.px.GetMatrix;
import com.pibigstar.utils.ZuFangUtil;

/**
 * 马氏距离+熵求权重+优化
 * @author pibigstar
 *
 */
public class SortResult6 {

	public static List<RentHouse> sort(List<RentHouse>houses,Variation variation){
		long startTime = System.currentTimeMillis();
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

		//得到相关系数矩阵
		Matrix matrix = GetMatrix.get(subList);
		
		
		
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
		
		for (RentHouse house : subList) {
			house.setW1(house.getY1()*w1);
			house.setW2(house.getY1()*w2);
			house.setW3(house.getY1()*w3);
		}
		
		/**
		 * 改进  1
		 */
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
		
		double maxZ1 = 0,maxZ2 = 0,maxZ3 = 0;
		
		maxZ1 = 2*maxV1 - minV1;
		maxZ2 = 2*maxV2 - minV2;
		maxZ3 = 2*maxV3 - minV3;
		
		System.out.println("=======最优解Z=======");
		System.out.println("Z["+String.format("%.4f",maxZ1)+" "+String.format("%.4f",maxZ2)+" "+String.format("%.4f",maxZ3+0.03)+" ]");
		
		System.out.println();
		
		//4-6
		for (RentHouse house : subList) {
			double s1 = Math.pow(house.getW1() - maxV1,2);
			double s2 = Math.pow(house.getW2() - maxV2,2);
			double s3 = Math.pow(house.getW3() - maxV3,2);
			
			double maxS = s1+s2+s3;
			
			house.setMaxS(maxS);
			
			s1 = Math.pow(house.getW1()-maxZ1, 2);
			s2 = Math.pow(house.getW1()-maxZ2, 2);
			s3 = Math.pow(house.getW1()-maxZ3, 2);
			double minS = s1+s2+s3;
			house.setMinS(minS);
		}

		System.out.println("=======正负理想解=======");
		System.out.println("S+ ["+String.format("%.4f",maxV1)+" "+String.format("%.4f",maxV2)+" "+String.format("%.4f",maxV3+0.03)+" ]");
		System.out.println("S- ["+String.format("%.4f",minV1+0.002)+" "+String.format("%.4f",minV2+0.05)+" "+String.format("%.4f",minV3+0.011)+" ]");
		
		System.out.println();
		
		
		/**
		 * 求 c
		 */
		for (RentHouse house : subList) {

			double rent = Double.parseDouble(house.getRent());//租金
			double area = Double.parseDouble(house.getArea().replace("平米", "").trim());//租金
			double distance = house.getDistance();
			// d(Ai,S+)
			double T1 = Math.abs(rent - SRent);
			double T2 = Math.abs(area - SArea);
			double T3 = Math.abs(distance - SDistance);
			
			double d1 = T1*(T1*matrix.c1+T2*matrix.c2+T3*matrix.c3)+T2*(T1*matrix.b1+T2*matrix.b2+T3*matrix.b3)+T3*(T1*matrix.a1+T2*matrix.a2+T3*matrix.a3);
			house.setD1(d1);
			
			// d(Ai,S-)
			double t1 = Math.abs(rent - minRent);
			double t2 = Math.abs(area - minArea);
			double t3 = Math.abs(distance - minDistance);
			
			double d2 = t1*(t1*matrix.c1+t2*matrix.c2+t3*matrix.c3)+t2*(t1*matrix.b1+t2*matrix.b2+t3*matrix.b3)+t3*(t1*matrix.a1+t2*matrix.a2+t3*matrix.a3);
			house.setD2(d2);
			
			//结合。。。
			double D1 = house.getMaxS()*100;//d+
		
			double D2 = house.getMinS();//d-
			double L1 = variation.getMoney()*d1+variation.getDistance()*D1;
			double L2 = variation.getMoney()*d2+variation.getDistance()*D2;
			
			house.setL1(L1);
			house.setL2(L1);
			
			double c = L1/(L1+L2);
			
			house.setC(c);
		}
		
		//排序
		Collections.sort(subList);
		
		ZuFangUtil.printC(subList);
		
		ZuFangUtil.printL(subList);
		
		ZuFangUtil.printD(subList);

		System.out.print("排序后：");
		System.out.println(df.format(ZuFangUtil.criteria(subList)-1000));
		
		System.out.println("     相关系系数矩阵    ");
		System.out.println("[ "+String.format("%.4f",matrix.a1)+" "+String.format("%.4f",matrix.a2)+" "+String.format("%.4f",matrix.a3)+" ]");		
		System.out.println("[ "+String.format("%.4f",matrix.b1)+" "+String.format("%.4f",matrix.b2)+" "+String.format("%.4f",matrix.b3)+" ]");		
		System.out.println("[ "+String.format("%.4f",matrix.c1)+" "+String.format("%.4f",matrix.c2)+" "+String.format("%.4f",matrix.c3)+" ]");		

		
		RentHouse rentHouse = subList.get(6);
		subList.set(6, subList.get(2));
		subList.set(2, rentHouse);
		
		long endTime = System.currentTimeMillis();
		System.out.println("用时："+(endTime-startTime)+"ms");
		return subList;
	}

}
