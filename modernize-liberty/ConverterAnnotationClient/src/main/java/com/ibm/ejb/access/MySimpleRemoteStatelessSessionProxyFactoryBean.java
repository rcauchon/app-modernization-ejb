package com.ibm.ejb.access;

import java.lang.reflect.InvocationTargetException;

import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import org.springframework.ejb.access.SimpleRemoteStatelessSessionProxyFactoryBean;

public class MySimpleRemoteStatelessSessionProxyFactoryBean extends SimpleRemoteStatelessSessionProxyFactoryBean {

	@Override
	protected Object create() throws NamingException, InvocationTargetException {
		System.out.println("MySimpleRemote: create()"); 	
		
		//String remoteURL = "corbaname::localhost:22809#ejb/global/TempEAR-0.0.1/com.ibm.temp-TempEJB-0.0.1/ConverterBean!com.ibm.temp.ejb.ConverterRemote";
		Object homeObject = this.lookup();
		String jndiValue = homeObject.toString();
		System.out.println("jndi = "  + jndiValue);

		if (homeObject instanceof String) {
			//Note if your spring mvc references a jndi is looked up again, you might have to do a double lookup
			homeObject = this.lookup(jndiValue);
		}
		Object myEJB = PortableRemoteObject.narrow(homeObject, this.getBusinessInterface());
		System.out.println("jndi = "  + myEJB);
	
		return myEJB;
	}
}
