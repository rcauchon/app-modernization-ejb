package com.ibm.common.controller;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

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
	
	private ConverterRemote lookupEJB(Temperature temp) {
		
		try {

			Context ctx = new InitialContext();
			          
            System.out.println("Server Hostname: "+ System.getenv("server_hostname"));
			System.out.println("Server iiop port: "+ System.getenv("server_iiop_port"));
			System.out.println("Server ejb remote: "+ System.getenv("server_ejb_remote_bean"));
			System.out.println("Server ejb remote: "+ System.getenv("server_ejb_remote"));

            String provider = "corbaname::" + System.getenv("server_hostname") + ":" + System.getenv("server_iiop_port");
			                    
			String ejbGlobalStr = "ejb/global/TempEAR-0.0.1/com.ibm.temp-TempEJB-0.0.1/ConverterBean!com.ibm.temp.ejb.ConverterRemote";

			Object homeObject = ctx.lookup(provider + "#" + ejbGlobalStr);
			ConverterRemote myRemoteEJB = (ConverterRemote) PortableRemoteObject.narrow(homeObject, ConverterRemote.class);

			return myRemoteEJB;
			
		} catch (NamingException ex) {
			System.out.println("Error with Remote EJB: " + ex.getMessage());
			temp.setMessage("Error: " + ex.getMessage());
		}
		return null;
	}

	@CrossOrigin
	@RequestMapping(value="/CtoF/{degree}", method = RequestMethod.GET)
	public @ResponseBody Temperature getTempCtoF(@PathVariable double degree) {

		Temperature temp = new Temperature(ConvertType.CtoF);
		
		ConverterRemote remote = lookupEJB(temp);
		
		if (remote != null) {
		
			double deg = remote.celsiusToFar(degree);
				
			temp.setMessage("Success");
			temp.setDegree(deg);
		} 
		return temp;
	}
	
	@CrossOrigin
	@RequestMapping(value="/CtoK/{degree}", method = RequestMethod.GET)
	public @ResponseBody Temperature getTempCtoK(@PathVariable double degree) {

		Temperature temp = new Temperature(ConvertType.CtoK);
		
		ConverterRemote remote = lookupEJB(temp);
		
		if (remote != null) {
		
			double deg = remote.celsiusToKelvin(degree);
				
			temp.setMessage("Success");
			temp.setDegree(deg);
		} 
		return temp;

	}
	
	@CrossOrigin
	@RequestMapping(value="/FtoC/{degree}", method = RequestMethod.GET)
	public @ResponseBody Temperature getTempFtoC(@PathVariable double degree) {

		Temperature temp = new Temperature(ConvertType.FtoC);
		
		ConverterRemote remote = lookupEJB(temp);
		
		if (remote != null) {
		
			double deg = remote.farenheitToCel(degree);
				
			temp.setMessage("Success");
			temp.setDegree(deg);
		} 
		return temp;

	}
	
	@CrossOrigin
	@RequestMapping(value="/FtoK/{degree}", method = RequestMethod.GET)
	public @ResponseBody Temperature getTempFtoK(@PathVariable double degree) {

		Temperature temp = new Temperature(ConvertType.FtoK);
		
		ConverterRemote remote = lookupEJB(temp);
		
		if (remote != null) {
		
			double deg = remote.farenheitToKelvin(degree);
				
			temp.setMessage("Success");
			temp.setDegree(deg);
		}
		return temp;

	}
	
	@CrossOrigin
	@RequestMapping(value="/KtoC/{degree}", method = RequestMethod.GET)
	public @ResponseBody Temperature getTempKtoC(@PathVariable double degree) {

		Temperature temp = new Temperature(ConvertType.KtoC);
		
		ConverterRemote remote = lookupEJB(temp);
		
		if (remote != null) {
		
			double deg = remote.kelvinToCel(degree);
				
			temp.setMessage("Success");
			temp.setDegree(deg);
		} 
		return temp;

	}
	
	@CrossOrigin
	@RequestMapping(value="/KtoF/{degree}", method = RequestMethod.GET)
	public @ResponseBody Temperature getTempInJSON(@PathVariable double degree) {

		Temperature temp = new Temperature(ConvertType.KtoF);
		
		ConverterRemote remote = lookupEJB(temp);
		
		if (remote != null) {
		
			double deg = remote.farenheitToCel(degree);
				
			temp.setMessage("Success");
			temp.setDegree(deg);
		} 
		return temp;

	}
}