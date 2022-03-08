package com.ibm.common.controller;

import java.rmi.RemoteException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ibm.common.model.ConvertType;
import com.ibm.common.model.Temperature;
import com.ibm.temp.ejb.ConverterRemote;

@Controller
@RequestMapping("/converter")
public class JSONController {
	
	@Autowired
	ConverterRemote remote;


	

	@CrossOrigin
	@RequestMapping(value="/CtoF/{degree}", method = RequestMethod.GET)
	public @ResponseBody Temperature getTempCtoF(@PathVariable double degree) throws RemoteException {

		Temperature temp = new Temperature(ConvertType.CtoF);

		
		if (remote != null) {
		
			double deg = remote.celsiusToFar(degree);
				
			temp.setMessage("Success");
			temp.setDegree(deg);
		} 
		return temp;
	}
	
	@CrossOrigin
	@RequestMapping(value="/CtoK/{degree}", method = RequestMethod.GET)
	public @ResponseBody Temperature getTempCtoK(@PathVariable double degree) throws RemoteException {

		Temperature temp = new Temperature(ConvertType.CtoK);

		
		if (remote != null) {
		
			double deg = remote.celsiusToKelvin(degree);
				
			temp.setMessage("Success");
			temp.setDegree(deg);
		} 
		return temp;

	}
	
	@CrossOrigin
	@RequestMapping(value="/FtoC/{degree}", method = RequestMethod.GET)
	public @ResponseBody Temperature getTempFtoC(@PathVariable double degree) throws RemoteException {

		Temperature temp = new Temperature(ConvertType.FtoC);
		
		if (remote != null) {
		
			double deg = remote.farenheitToCel(degree);
				
			temp.setMessage("Success");
			temp.setDegree(deg);
		} 
		return temp;

	}
	
	@CrossOrigin
	@RequestMapping(value="/FtoK/{degree}", method = RequestMethod.GET)
	public @ResponseBody Temperature getTempFtoK(@PathVariable double degree) throws RemoteException {

		Temperature temp = new Temperature(ConvertType.FtoK);
		
		if (remote != null) {
		
			double deg = remote.farenheitToKelvin(degree);
				
			temp.setMessage("Success");
			temp.setDegree(deg);
		}
		return temp;

	}
	
	@CrossOrigin
	@RequestMapping(value="/KtoC/{degree}", method = RequestMethod.GET)
	public @ResponseBody Temperature getTempKtoC(@PathVariable double degree) throws RemoteException {

		Temperature temp = new Temperature(ConvertType.KtoC);
		
		if (remote != null) {
		
			double deg = remote.kelvinToCel(degree);
				
			temp.setMessage("Success");
			temp.setDegree(deg);
		} 
		return temp;

	}
	
	@CrossOrigin
	@RequestMapping(value="/KtoF/{degree}", method = RequestMethod.GET)
	public @ResponseBody Temperature getTempInJSON(@PathVariable double degree) throws RemoteException {

		Temperature temp = new Temperature(ConvertType.KtoF);
		
		if (remote != null) {
		
			double deg = remote.farenheitToCel(degree);
				
			temp.setMessage("Success");
			temp.setDegree(deg);
		} 
		return temp;

	}
}