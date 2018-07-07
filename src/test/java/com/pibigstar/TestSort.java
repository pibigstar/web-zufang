package com.pibigstar;

import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pibigstar.zufang.domain.RentHouse;
import com.pibigstar.zufang.domain.Variation;
import com.pibigstar.zufang.repository.RentHouseRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestSort {
	@Autowired  
	private RentHouseRepository repository; 

	@Test
	public List<RentHouse> sort(Variation variation){
		
		
		List<RentHouse> rentHouses = repository.findAll();
		
		List<RentHouse> subList = rentHouses.subList(0, 200);
		
		double minRent = 0 ,minArea = 0,minDistance = 0;
		
		//寻找出每个属性的最小值
		for (RentHouse house : subList) {
			double longitude = Double.parseDouble(house.getLongitude());
			double dimension = Double.parseDouble(house.getDimension());
			
			double rent = Double.parseDouble(house.getRent());//租金
			double area = Double.parseDouble(house.getArea().replace("平方", "").trim());//租金
			double distance = Math.sqrt(Math.pow(longitude-variation.getLongitude(), 2)+Math.pow(dimension-variation.getDimension(), 2));
			house.setDistance(distance);
			
			if(minRent>rent) {
				minRent = rent;
			}
			if (minArea>area) {
				minArea = area;
			}
			if (minDistance>distance) {
				minDistance = distance;
			}
		}
		/**
		 * 	q1  面积
		 *	q2  租金
		 *	q3  距离
		 */
		//4-1
		for (RentHouse house : subList) {
			double rent = Double.parseDouble(house.getRent());//租金
			double area = Double.parseDouble(house.getArea().replace("平方", "").trim());//租金
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
		 * 开始求权值
		 * 
		 * 离差求权值法
		 */
		for (RentHouse house : subList) {
			
			double y1 = house.getY1();
			double y2 = house.getY2();
			double y3 = house.getY3();
			double sumY1 = 0, sumY2 =0, sumY3 = 0;
			
			for (RentHouse house2 : subList) {
				double t1 = Math.abs(y1 - house2.getY1());
				double t2 = Math.abs(y2 - house2.getY2());
				double t3 = Math.abs(y3 - house2.getY3());
				sumY1+=t1;
				sumY2+=t2;
				sumY3+=t3;
			}
			house.setV1(sumY1);
			house.setV2(sumY2);
			house.setV3(sumY3);
		}
		
		double sumV1 = 0,sumV2 = 0, sumV3 = 0;
		for (RentHouse house : subList) {
			double v1 = house.getV1();
			double v2 = house.getV2();
			double v3 = house.getV3();
			
			sumV1 +=v1;
			sumV2 +=v2;
			sumV3 +=v3;
		}
		
		double w1 = 0,w2 = 0,w3 = 0;
		double sumV = sumV1+sumV2+sumV3;
		w1 = sumV1 / sumV; // 面积的权值
		w2 = sumV2 / sumV; // 租金的权值
		w3 = sumV3 / sumV; // 距离的权值
		
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
		
		return subList;
	}

}
