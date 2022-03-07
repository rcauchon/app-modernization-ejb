package com.ibm.common.model;

public class Temperature {

	ConvertType type;
	
	double degree = -9999;
	
	String message = "Error: Occur check the log";
	
	// Default constructor
	public Temperature(ConvertType pType) {
		type = pType;
	}

	public ConvertType getType() {
		return type;
	}

	public void setType(ConvertType type) {
		this.type = type;
	}

	public double getDegree() {
		return degree;
	}

	public void setDegree(double degree) {
		this.degree = degree;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	} 
}