package com.example.demo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility  =JsonAutoDetect.Visibility.ANY)

public class StockValues {
	
	private int stock_id;
	private String date;
	private float value;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

}
