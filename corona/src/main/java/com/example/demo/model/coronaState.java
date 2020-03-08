package com.example.demo.model;

public class coronaState {

	private String city ;
	private String country;
	private int totalConfirmCases;
	private int Delta;
	
	
	public int getDelta() {
		return Delta;
	}
	public void setDelta(int delta) {
		Delta = delta;
	}
	public coronaState() {
//		super();
	}
	public coronaState(String city, String country, int totalConfirmCases) {
//		super();
		this.city = city;
		this.country = country;
		this.totalConfirmCases = totalConfirmCases;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getTotalConfirmCases() {
		return totalConfirmCases;
	}
	public void setTotalConfirmCases(int totalConfirmCases) {
		this.totalConfirmCases = totalConfirmCases;
	}
	
	
}
