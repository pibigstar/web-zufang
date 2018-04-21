package com.pibigstar.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.pibigstar.domain.Variation;

public class GetPositionByName {
	
	public static Variation get(String name) {
		String address = "";
        String lat = "";
        String lng = "";
        try {  
            address = java.net.URLEncoder.encode(name,"UTF-8");  
        } catch (UnsupportedEncodingException e1) {  
            e1.printStackTrace();  
        } 
        String url = String.format("http://api.map.baidu.com/geocoder/v2/?"
        +"ak=4rcKAZKG9OIl0wDkICSLx8BA&output=json&address=%s",address);
        URL myURL = null;
        URLConnection httpsConn = null;  
        //进行转码
        try {
            myURL = new URL(url);
        } catch (MalformedURLException e) {

        }
        try {
            httpsConn = (URLConnection) myURL.openConnection();
            if (httpsConn != null) {
                InputStreamReader insr = new InputStreamReader(
                        httpsConn.getInputStream(), "UTF-8");
                BufferedReader br = new BufferedReader(insr);
                String data = null;
                if ((data = br.readLine()) != null) {
                    lat = data.substring(data.indexOf("\"lat\":") 
                    + ("\"lat\":").length(), data.indexOf("},\"precise\""));
                    lng = data.substring(data.indexOf("\"lng\":") 
                    + ("\"lng\":").length(), data.indexOf(",\"lat\""));
                }
                insr.close();
            }
        } catch (IOException e) {

        }
		
		Variation variation = new Variation();
		variation.setLongitude(Double.parseDouble(lng));
		variation.setDimension(Double.parseDouble(lat));
		return variation;
		
	}
	
	public static void main(String[] args) {
		Variation variation = get("郑州轻工业学院");
		System.out.println(variation.getLongitude());
		System.out.println(variation.getDimension());
	}

}
