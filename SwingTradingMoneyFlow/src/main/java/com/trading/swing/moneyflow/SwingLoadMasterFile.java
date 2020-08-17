package com.trading.swing.moneyflow;

import static com.trading.swing.moneyflow.HeaderEnum.SYMBOL;
import static com.trading.swing.moneyflow.HeaderEnum.VALUE_OF_SECURITY;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.opencsv.CSVWriter;

public class SwingLoadMasterFile extends SwingLoadFile
{
	private static final long VALUE_THRESHOLD = 10000000;
	
	private static final String FILE_NAME = "CF-Insider-Trading-equities-17-05-2020-to-17-08-2020.csv";
	
	private static final String OUTPUT_FILE_NAME = "CompanyList";

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
		
		Set<String> symbolSet = finalSymbolValueMap.keySet().stream().sorted().collect(Collectors.toCollection(LinkedHashSet::new));
		
		System.out.println(symbolSet.size());
		
		System.out.println(symbolSet);
		
		LocalDate today = LocalDate.now();
		
		try(FileWriter outputfile = new FileWriter(FILES_DIR_PATH + File.separator + OUTPUT_FILE_NAME + "-" + today + ".csv");
			CSVWriter csvWriter = new CSVWriter(outputfile);)
		{
	        symbolSet.stream().forEach(symbol -> csvWriter.writeNext(new String[]{symbol}));
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		} 
	}
}
