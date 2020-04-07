package com.spring.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringMain 
{
	public static void main(String[] args) 
	{
		//ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		
		HelloWorld helloWorld = (HelloWorld) context.getBean("helloWorld");
		
		System.out.println(helloWorld.getMessage());

	}

}
