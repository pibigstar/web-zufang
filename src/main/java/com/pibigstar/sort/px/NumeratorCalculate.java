package com.pibigstar.sort.px;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author alan-king
 * 
 * the class is going to calculate the numerator;
 * 
 *
 */
public class NumeratorCalculate {
	
	//add global varieties
	protected List<String> xList , yList;
	
	public NumeratorCalculate(List<String> xList ,List<String> yList){
		this.xList = xList;
		this.yList = yList;
	}
	
	/**
	 * 协方差计算
	 * add operate method
	 */
	public double calcuteNumerator(){
		double result =0.0;
		double xAverage = 0.0;
		double temp = 0.0;
		
		int xSize = xList.size();
		for(int x=0;x<xSize;x++){
			temp += Double.parseDouble(xList.get(x));
		}
		xAverage = temp/xSize;
		
		double yAverage = 0.0;
		temp = 0.0;
		int ySize = yList.size();
		for(int x=0;x<ySize;x++){
			temp += Double.parseDouble(yList.get(x));
		}
		yAverage = temp/ySize;
		
		//double sum = 0.0;
		for(int x=0;x<xSize;x++){
			result+=(Double.parseDouble(xList.get(x))-xAverage)*(Double.parseDouble(yList.get(x))-yAverage);
		}
		return result;
	}
}
