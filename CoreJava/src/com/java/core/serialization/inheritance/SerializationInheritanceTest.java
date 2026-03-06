package com.java.core.serialization.inheritance;

import com.java.core.serialization.util.SerializationUtil;

public class SerializationInheritanceTest 
{
	public static void main(String[] args) 
	{
		String fileName="vehicle.ser";
		Car car = new Car();
		car.setType("Sedan");
		car.setSpeed(200); //setting super class variable
		car.setHeight(50);
		car.setWidth(200);
		
		SerializationUtil.serialize(car, fileName);
		
		Car deserializedCar = (Car) SerializationUtil.deserialize(fileName);
		
		System.out.println("car Object::" + car);
		System.out.println("deserializedCar Object::" + deserializedCar);
	}
}
