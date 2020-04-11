package com.spring.core;

import org.springframework.stereotype.Component;

@Component
public class Bike extends Vehicle {

	public void drive()
	{
		System.out.println("Driving a bike");
	}
}
