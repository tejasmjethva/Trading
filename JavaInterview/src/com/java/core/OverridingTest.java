package com.java.core;

public class OverridingTest 
{
	public static void main(String[] args) 
	{
		Animal a = new Dog();
		
		//a.eat("abc");
	}
}

class Animal 
{
	private String name;
	
	public Animal(String name) 
	{
		this.name = name;
	}
	
	public Animal() 
	{
		
	}
	
	public void eat()
	{
		System.out.println("Animal is eating");
	}
}

class Dog extends Animal
{
	public void eat()
	{
		System.out.println("Animal is eating");
	}
	
	public void eat(String name)
	{
		System.out.println(name + " is eating");
	}
}