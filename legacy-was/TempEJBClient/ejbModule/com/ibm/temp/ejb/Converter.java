package com.ibm.temp.ejb;

public interface Converter {

	public double celsiusToFar(double celsius);
	public double celsiusToKelvin(double celsius);
	
	public double farenheitToCel(double farenheit);
	public double farenheitToKelvin(double farenheit);
	
	public double kelvinToFar(double kelvin);
	public double kelvinToCel(double kelvin);
	
}
