package com.spring.core;

import org.springframework.stereotype.Component;

@Component
//@Primary
public class Car extends Vehicle {

	public void drive()
	{
		System.out.println("Driving a car");
	}
}
