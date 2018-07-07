package com.pibigstar.zufang.sort.px;

import java.util.ArrayList;
import java.util.List;

/**
 * 求两个列表的相关系数
 * @author pibigstar
 *
 */
public class CallClass {
	
	public static final double getCOV(List<String> list1,List<String> list2) {
		double CORR = 0.0;
		
		NumeratorCalculate nc = new NumeratorCalculate(list1,list2);
		double numerator = nc.calcuteNumerator();
		DenominatorCalculate dc = new DenominatorCalculate();
		double denominator = dc.calculateDenominator(list1, list2);
		CORR = numerator/denominator;
		return CORR;
	}
	
}
