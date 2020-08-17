package com.trading.swing.moneyflow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.trading.swing.moneyflow.HeaderEnum.VALUE_OF_SECURITY;
import static com.trading.swing.moneyflow.HeaderEnum.NUMBER_OF_SECURITITES;

public class SwingLoadIndividualFile extends SwingLoadFile
{
	private static final String FILE_NAME = "CF-Insider-Trading-equities-HEROMOTOCO-17-Aug-2020.csv";
	
	public static void main(String[] args) 
	{
		Map<Integer, String> indexHeaderMap = new HashMap<>();

		List<Map<String, String>> dataList = new ArrayList<>();

		populateDataList(dataList, indexHeaderMap, FILE_NAME, true);

		System.out.println(dataList);

		double averageValue = calculateAveragePrice(dataList);
		
		System.out.println(averageValue); 
	}

	private static double calculateAveragePrice(List<Map<String, String>> dataList) 
	{
		long totalSecurities = 0;

		double totalValue = 0;

		for (Map<String, String> map : dataList) 
		{
			totalSecurities = totalSecurities + Long.parseLong(map.get(NUMBER_OF_SECURITITES.getHeader()));

			totalValue = totalValue + Double.parseDouble(map.get(VALUE_OF_SECURITY.getHeader()));

		}
		
		return totalValue / totalSecurities;
	}
}
