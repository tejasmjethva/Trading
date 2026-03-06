package com.java.core.serialization;

import com.java.core.serialization.util.SerializationUtil;

public class SerializationTest 
{
	public static void main(String[] args) 
	{
		String fileName="employee.ser";
		Employee emp = new Employee();
		emp.setId(100);
		emp.setName("Emp1");
		emp.setSalary(5000);
		
		//SerializationUtil.serialize(emp, fileName);
		
		Employee empNew = (Employee) SerializationUtil.deserialize(fileName);
		
		System.out.println("emp Object::" + emp);
		System.out.println("empNew Object::" + empNew);
	}
}
