package com.spring.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class PropertyCache 
{
	@Value("${sourceLocation}")
	public String sourceValue;
	
	@Value("${jdbc.driverClassName}")
	public String driverClassName;
	
	@Autowired
	private Environment env;
	
	public void propertiesValue()
	{
		System.out.println(env.getProperty("sourceLocation"));
		
		System.out.println(this.driverClassName);
	}
}
