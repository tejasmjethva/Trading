package com.spring.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Driver {

	@Autowired
	@Qualifier("bike")
	private Vehicle vehicle;
	
	@Autowired
	private PropertyCache fileService;
	
	public void drive()
	{
		vehicle.drive();
		
		//System.out.println(fileService.driverClassName);
	}
}
