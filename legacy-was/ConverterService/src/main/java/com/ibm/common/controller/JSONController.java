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
		Context ctx;
		
		try {
			Properties props = new Properties();
			props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.ibm.websphere.naming.WsnInitialContextFactory");
			props.setProperty(Context.PROVIDER_URL, "corbaloc:iiop:localhost:2809");
			
			ctx = new InitialContext(props);
			
			Object homeObject = ctx.lookup("ejb/ConverterRemote");
			ConverterRemote myRemoteEJB = (ConverterRemote) PortableRemoteObject.narrow(homeObject, ConverterRemote.class);

			return myRemoteEJB;
			
		} catch (NamingException ex) {
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
		} else {
			temp.setMessage("Success");
		}
		return temp;
	}
	
	@RequestMapping(value="/FtoC/{degree}", method = RequestMethod.GET)
	public @ResponseBody Temperature getTempInJSON(@PathVariable double degree) {

		Temperature temp = new Temperature(ConvertType.FtoC);
		
		ConverterRemote remote = lookupEJB(temp);
		
		if (remote != null) {
		
			double deg = remote.farenheitToCel(degree);
				
			temp.setMessage("Success");
			temp.setDegree(deg);
		} else {
			temp.setMessage("Success");
		}
		return temp;

	}
}