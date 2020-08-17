package com.trading.swing.moneyflow;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.opencsv.CSVReader;

import static com.trading.swing.moneyflow.HeaderEnum.*;

public class SwingLoadFile
{
	private static final List<String> PROMOTERS_FILTER_VALUES = Arrays.asList("Promoter Group","Promoters");

	private static final List<String> MARKET_PURCHASE_FILTER_VALUES = Arrays.asList("Market Purchase");

	private static final List<String> HEADERS = Arrays.asList(SYMBOL.getHeader(), VALUE_OF_SECURITY.getHeader(),
			CATEGORY_OF_PERSON.getHeader(), MODE_OF_ACQUISITION.getHeader(), NUMBER_OF_SECURITITES.getHeader());

	protected static final String FILES_DIR_PATH = "C:\\Users\\hima\\Downloads\\tejas\\Work\\SwingFiles\\";
	
	protected static void populateDataList(List<Map<String, String>> dataList, Map<Integer, String> indexHeaderMap, 
			String fileName, boolean isLoadingIndividualFile) 
	{
		String csvFile = FILES_DIR_PATH + fileName;

		CSVReader reader = null;

		String[] line;

		boolean firstRow = true;
		
		try 
		{	
			reader = new CSVReader(new FileReader(csvFile));

			while ((line = reader.readNext()) != null) 
			{
				if(firstRow)
				{
					int index = 0;

					for (String value : line)
					{
						value = value.trim().contains(SYMBOL.getHeader()) ? SYMBOL.getHeader() : value.trim();

						if(CollectionUtils.containsAny(HEADERS, value))
						{
							indexHeaderMap.put(index, value);
						}

						index++;
					}

					firstRow = false;
				}
				else
				{
					Map<String, String> dataMap = new HashMap<>();

					int index = 0;

					for (String value : line)
					{
						if(indexHeaderMap.keySet().contains(index))
						{
							dataMap.put(indexHeaderMap.get(index), value);
						}

						index++;
					}
					
					if(isLoadingIndividualFile && StringUtils.containsIgnoreCase(dataMap.get(MODE_OF_ACQUISITION.getHeader()), "Sell"))
					{
						System.err.println("Company: " + dataMap.get(SYMBOL.getHeader()) + " contains selling data");
						
						return;
					}

					if(CollectionUtils.containsAny(PROMOTERS_FILTER_VALUES, dataMap.get(CATEGORY_OF_PERSON.getHeader()))
							&& CollectionUtils.containsAny(MARKET_PURCHASE_FILTER_VALUES, dataMap.get(MODE_OF_ACQUISITION.getHeader())))
					{
						dataList.add(dataMap);
					}
				}
			}
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
