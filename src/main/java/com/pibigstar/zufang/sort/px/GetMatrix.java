package com.pibigstar.zufang.sort.px;

import java.util.ArrayList;
import java.util.List;

import com.pibigstar.zufang.domain.Matrix;
import com.pibigstar.zufang.domain.RentHouse;

public class GetMatrix {

	public static final Matrix get(List<RentHouse> houses) {

		List<String> list1 = new ArrayList<>();
		List<String> list2 = new ArrayList<>();
		List<String> list3 = new ArrayList<>();

		for (RentHouse house : houses) {
			list1.add(house.getRent());//租金
			list2.add(house.getArea().replace("平米", "").trim());//租金
			list3.add(house.getDistance()+"");
		}

		double a1 = CallClass.getCOV(list1, list1);
		double a2 = CallClass.getCOV(list1, list2);
		double a3 = CallClass.getCOV(list1, list3);
		double b1 = CallClass.getCOV(list2, list1);
		double b2 = CallClass.getCOV(list2, list2);
		double b3 = CallClass.getCOV(list2, list3);
		double c1 = CallClass.getCOV(list3, list1);
		double c2 = CallClass.getCOV(list3, list2);
		double c3 = CallClass.getCOV(list3, list3);

		Matrix matrix = new Matrix();
		matrix.a1 = a1;
		matrix.a2 = a2;
		matrix.a3 = a3;
		matrix.b1 = b1;
		matrix.b2 = b2;
		matrix.b3 = b3;
		matrix.c1 = c1;
		matrix.c2 = c2;
		matrix.c3 = c3;
		return matrix;
	}

}
