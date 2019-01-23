package com.example.ajax.bean;

import lombok.Data;

@Data
public class City {

	private String cityCode;
	private String cityName;
	public City(){

	}
	public City(String cityCode, String cityName) {
		super();
		this.cityCode = cityCode;
		this.cityName = cityName;
	}


}
