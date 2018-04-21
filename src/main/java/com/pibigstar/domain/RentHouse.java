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
	private String imgUrl;//租金
	private String longitude;//经度
	private String dimension;//纬度
	private double weight; // 权重
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
	@Override
	public String toString() {
		return "RentHouse [id=" + id + ", name=" + name + ", local=" + local + ", village=" + village + ", position="
				+ position + ", url=" + url + ", rent=" + rent + ", imgUrl=" + imgUrl + ", longitude=" + longitude
				+ ", dimension=" + dimension + "]";
	}
	@Override
	public int compareTo(RentHouse o) {
		if (this.getWeight()>o.getWeight()) {
			return 1;
		}else if (this.getWeight()<o.getWeight()){
			return -1;
		}else {
			return 0;
		}
	}
}
