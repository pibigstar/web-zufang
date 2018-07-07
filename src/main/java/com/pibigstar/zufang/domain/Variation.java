package com.pibigstar.zufang.domain;

public class Variation {
	
	private double money;
	private double distance;
	private double longitude;
	private double dimension;
	
	private int choose;
	
	
	public int getChoose() {
		return choose;
	}
	public void setChoose(int choose) {
		this.choose = choose;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getDimension() {
		return dimension;
	}
	public void setDimension(double dimension) {
		this.dimension = dimension;
	}
	@Override
	public String toString() {
		return "Variation [money=" + money + ", distance=" + distance + ", longitude=" + longitude + ", dimension="
				+ dimension + "]";
	}
	
}
