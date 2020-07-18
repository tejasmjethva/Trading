package com.trading.swing.moneyflow;

import static com.trading.swing.moneyflow.HeaderEnum.SYMBOL;
import static com.trading.swing.moneyflow.HeaderEnum.VALUE_OF_SECURITY;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SwingLoadMasterFile extends SwingLoadFile
{
	private static final long VALUE_THRESHOLD = 10000000;
	
	private static final String FILE_NAME = "CF-Insider-Trading-equities-18-04-2020-to-18-07-2020.csv";

	public static void main(String[] args) 
	{
		Map<Integer, String> indexHeaderMap = new HashMap<>();
		List<Map<String, String>> dataList = new ArrayList<>();
		
		populateDataList(dataList, indexHeaderMap, FILE_NAME, false);

		Map<String, Long> symbolValueConsolidatedMap = applyFilters(dataList);
		
		Map<String, Long> finalSymbolValueMap = applyThreshold(symbolValueConsolidatedMap);
		
		display(indexHeaderMap, symbolValueConsolidatedMap, finalSymbolValueMap);
	}

	private static Map<String, Long> applyFilters(List<Map<String, String>> dataList) 
	{
		Map<String, Long> symbolValueConsolidatedMap = new HashMap<>();
		
		for (Map<String, String> map : dataList) 
		{
			if(symbolValueConsolidatedMap.get(map.get(SYMBOL.getHeader())) == null)
			{
				symbolValueConsolidatedMap.put(map.get(SYMBOL.getHeader()), Long.parseLong(map.get(VALUE_OF_SECURITY.getHeader())));
			}
			else
			{
				long value = symbolValueConsolidatedMap.get(map.get(SYMBOL.getHeader()));

				symbolValueConsolidatedMap.put(map.get(SYMBOL.getHeader()), value + Long.parseLong(map.get(VALUE_OF_SECURITY.getHeader())));
			}
		}
		
		return symbolValueConsolidatedMap;
	}
	
	private static Map<String, Long> applyThreshold(Map<String, Long> symbolValueConsolidatedMap) 
	{
		Map<String, Long> finalSymbolValueMap = new HashMap<>();

		for (String symbol : symbolValueConsolidatedMap.keySet()) 
		{
			long value = symbolValueConsolidatedMap.get(symbol);

			if(value >= VALUE_THRESHOLD)
			{
				finalSymbolValueMap.put(symbol, value);
			}
		}
		
		return finalSymbolValueMap;
	}
	
	private static void display(Map<Integer, String> indexHeaderMap, Map<String, Long> symbolValueConsolidatedMap, 
			Map<String, Long> finalSymbolValueMap) 
	{
		//System.out.println(indexHeaderMap);
		//System.out.println(symbolValueConsolidatedMap);
		
		//System.out.println(finalSymbolValueMap);
		
		finalSymbolValueMap.keySet().stream().sorted().forEach(symbol -> System.out.println(symbol));
		
		System.out.println(finalSymbolValueMap.keySet().size());
	}
}
