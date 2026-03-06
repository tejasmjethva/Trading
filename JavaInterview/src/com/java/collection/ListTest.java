package com.java.collection;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListTest 
{
	public static void main(String[] args) 
	{
		List<Integer> list = Arrays.asList(1, 5, 2, 6, 9, 20, 31);
		
		for (Integer number : list) 
		{
			System.out.println(number);
		}
		
		//List<Integer> list = Arrays.asList(1, 5, 2, 6, 9, 20, 31);
		//printList(list);
		
		//find 2 numbers with given sum
		int[] array = {2, 4, 5, 5, 7, 1};
		int sum = 10;
		
		//3rd highest number from the array
		
		//Sort Map based on value
		
		Map<String, Integer> map = new HashMap<>();
		map.put("value1", 20);
		map.put("value3", 10);
		map.put("value4", 30);
		map.put("value5", 25);
	}
	
	public static void printList(List<Object> list)
	{
		for (Object object : list) 
		{
			System.out.println(object.toString());
		}
	}
}
