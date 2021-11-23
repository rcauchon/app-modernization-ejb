package com.ibm.common.controller;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import org.springframework.stereotype.Controller;
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
		//	Properties props = new Properties();
		//	props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.ibm.websphere.naming.WsnInitialContextFactory");
		//	props.setProperty(Context.PROVIDER_URL, "corbaname::DESKTOP-JR1BRQA:22809");
			
		//	ctx = new InitialContext(props);
			Context ctx = new InitialContext();
			                                                               //     TempEAR-0.0.1-SNAPSHOT/com.ibm.temp-TempEJB-0.0.1-SNAPSHOT/ConverterBean!com.ibm.temp.ejb.ConverterRemote
			Object homeObject = ctx.lookup("corbaname::localhost:22809#ejb/global/TempEAR-0.0.1/com.ibm.temp-TempEJB-0.0.1/ConverterBean!com.ibm.temp.ejb.ConverterRemote");
			ConverterRemote myRemoteEJB = (ConverterRemote) PortableRemoteObject.narrow(homeObject, ConverterRemote.class);

			return myRemoteEJB;
			
		} catch (NamingException ex) {
			System.out.println("Error with Remote EJB: " + ex.getMessage());
			temp.setMessage("Error: " + ex.getMessage());
		}
		return null;
	}

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