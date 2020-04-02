package com.java.core.serialization.inheritance;

import java.io.Serializable;

public class Vehicle implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int speed;

	/**
	 * If super class(Vehicle) doesn't implements the Serializable, then it should have default constructor.
	 * Because while deserializing an object, it creates an of the super class which doesn't implement Serializable.
	 * If all super classes implement Serializable, then Object class's instance is created.
	 */
	public Vehicle() 
	{
		System.out.println("Vehicle's class constructor called"); 
	}
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
}
