package com.spring.core;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan("com.spring.core")
@PropertySource("classpath:application.properties")
public class ApplicationConfig
{
	@Bean
	public HelloWorld helloWorld() 
	{
		HelloWorld helloWorld = new HelloWorld("HelloWorld !!!");
		helloWorld.setMessage("HelloWorld !!!");
		return helloWorld;
	}
	
	@Bean
	@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public Employee employee()
	{
		Employee employee = new Employee();
		employee.setName("John");
		employee.setTransportService(transportService());
		
		return employee;
	}
	
	@Bean
	public TransportService transportService()
	{
		TransportService transportService = new TransportService();
		transportService.setTransportMode("Car");
		return transportService;
	}
}
