package com.ibm.temp.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class Converter
 */
@Stateless
@Local(ConverterLocal.class)
@Remote(ConverterRemote.class)
public class ConverterBean implements ConverterRemote, ConverterLocal {

    /**
     * Default constructor. 
     */
    public ConverterBean() {
       
    }

	@Override
	public double celsiusToFar(double celsius) {
		double result = celsius * 9/5 + 32;
		System.out.println("From Celsius: " + celsius + " to Farenheit: "+ result);
		return result;
	}

	@Override
	public double farenheitToCel(double farenheit) {
		double result = farenheit * 1.8 + 32;
		System.out.println("From Farenheit: " + farenheit + " to celsius: "+ result);
		return result;
	}
	@Override
	public double celsiusToKelvin(double celsius) {
		double result = celsius + 273.15;
		System.out.println("From Celsius: " + celsius + " to Kelvin: "+ result);
		return result;
	}
	
	@Override
	public double farenheitToKelvin(double farenheit) {
		double result = (farenheit -32) / 1.8 + 273.15;
		System.out.println("From Farenheit: " + farenheit + " to Kelvin: "+ result);
		return result;
	}
	
	@Override
	public double kelvinToFar(double kelvin) {
		double result = kelvin * 1.8 - 549.67;
		System.out.println("From Kelvin: " + kelvin + " to Farenheit: "+ result);
		return result;
	}
	
	@Override
	public double kelvinToCel(double kelvin) {
		double result = kelvin - 273.15;
		System.out.println("From Kelvin: " + kelvin + " to Celsius: "+ result);
		return result;
	}

}
