package com.spring.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig
{
	@Bean
	public HelloWorld helloWorld() 
	{
		HelloWorld helloWorld = new HelloWorld();
		helloWorld.setMessage("HelloWorld !!!");
		return helloWorld;
	}
}
