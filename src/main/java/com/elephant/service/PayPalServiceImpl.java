 package com.elephant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.elephant.config.PayPalConfig;

@Service("payPalService")
public class PayPalServiceImpl implements PayPalService{
	
	@Autowired
	private Environment environment;

	@Override
	public PayPalConfig getPayPalConfig() {
		PayPalConfig payPalConfig=new PayPalConfig();
		payPalConfig.setAuthToken(environment.getProperty("PayPal.authtoken"));
		payPalConfig.setBusiness(environment.getProperty("PayPal.business"));
		payPalConfig.setPosturl(environment.getProperty("PayPal.posturl"));
		payPalConfig.setReturnurl(environment.getProperty("PayPal.returnurl"));
		
		return payPalConfig;
		
	}
	

	

	
	
	

}
