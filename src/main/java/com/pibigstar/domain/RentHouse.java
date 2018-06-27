package com.pibigstar.domain;

import org.springframework.data.annotation.Id;

public class RentHouse implements Comparable<RentHouse>{
	@Id
	private Integer id;
	private String name; //房源名
	private String local; //租房地址
	private String village; //租房小区
	private String position; //租房小区
	private String url; //房源链接
	private String rent;//租金
	private String area;//面积
	private String imgUrl;//图片链接
	private String longitude;//经度
	private String dimension;//纬度
	private double weight; // 权重
	private double distance; // 距离
	
	private double q1;//面积
	private double q2;//租金
	private double q3;//距离
	private double y1;//面积 的q1`
	private double y2;//租金 的q2`
	private double y3;//距离 的q3`
	
	private double p1;//Pij
	private double p2;//
	private double p3;//
	
	
	private double w1;//面积 的权值
	private double w2;//租金 的权值
	private double w3;//距离 的权值
	
	private double v1;
	private double v2;
	private double v3;
	
	private double L1;
	private double L2;
	
	private double maxS;
	private double minS;
	
	private double D1;
	private double D2;
	
	private double c;
	
	
	
	public double getL1() {
		return L1;
	}
	public void setL1(double l1) {
		L1 = l1;
	}
	public double getL2() {
		return L2;
	}
	public void setL2(double l2) {
		L2 = l2;
	}
	public double getP1() {
		return p1;
	}
	public void setP1(double p1) {
		this.p1 = p1;
	}
	public double getP2() {
		return p2;
	}
	public void setP2(double p2) {
		this.p2 = p2;
	}
	public double getP3() {
		return p3;
	}
	public void setP3(double p3) {
		this.p3 = p3;
	}
	public double getD1() {
		return D1;
	}
	public void setD1(double d1) {
		D1 = d1;
	}
	public double getD2() {
		return D2;
	}
	public void setD2(double d2) {
		D2 = d2;
	}
	public double getC() {
		return c;
	}
	public void setC(double c) {
		this.c = c;
	}
	public double getMaxS() {
		return maxS;
	}
	public void setMaxS(double maxS) {
		this.maxS = maxS;
	}
	public double getMinS() {
		return minS;
	}
	public void setMinS(double minS) {
		this.minS = minS;
	}
	public double getV1() {
		return v1;
	}
	public void setV1(double v1) {
		this.v1 = v1;
	}
	public double getV2() {
		return v2;
	}
	public void setV2(double v2) {
		this.v2 = v2;
	}
	public double getV3() {
		return v3;
	}
	public void setV3(double v3) {
		this.v3 = v3;
	}
	public double getW1() {
		return w1;
	}
	public void setW1(double w1) {
		this.w1 = w1;
	}
	public double getW2() {
		return w2;
	}
	public void setW2(double w2) {
		this.w2 = w2;
	}
	public double getW3() {
		return w3;
	}
	public void setW3(double w3) {
		this.w3 = w3;
	}
	public double getY1() {
		return y1;
	}
	public void setY1(double y1) {
		this.y1 = y1;
	}
	public double getY2() {
		return y2;
	}
	public void setY2(double y2) {
		this.y2 = y2;
	}
	public double getY3() {
		return y3;
	}
	public void setY3(double y3) {
		this.y3 = y3;
	}
	public double getQ1() {
		return q1;
	}
	public void setQ1(double q1) {
		this.q1 = q1;
	}
	public double getQ2() {
		return q2;
	}
	public void setQ2(double q2) {
		this.q2 = q2;
	}
	public double getQ3() {
		return q3;
	}
	public void setQ3(double q3) {
		this.q3 = q3;
	}
	
	
	/**===============*/
	
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getRent() {
		return rent;
	}
	public void setRent(String rent) {
		this.rent = rent;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getDimension() {
		return dimension;
	}
	public void setDimension(String dimension) {
		this.dimension = dimension;
	}
	
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	
	
	@Override
	public String toString() {
		return "RentHouse [name=" + name + ", rent=" + rent + ", area=" + area + ", w1=" + w1 + ", w2=" + w2 + ", w3="
				+ w3 + ", c=" + c + "]";
	}
	@Override
	public int compareTo(RentHouse o) {
		if (this.getC()<o.getC()) {
			return 1;
		}else if (this.getC()>o.getC()){
			return -1;
		}else {
			return 0;
		}
	}
}
