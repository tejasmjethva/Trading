package com.trading.swing.moneyflow;

public enum HeaderEnum 
{
	SYMBOL("SYMBOL"),
	VALUE_OF_SECURITY("VALUE OF SECURITY (ACQUIRED/DISPLOSED)"),
	CATEGORY_OF_PERSON("CATEGORY OF PERSON"),
	MODE_OF_ACQUISITION("MODE OF ACQUISITION"),
	NUMBER_OF_SECURITITES("NO. OF SECURITIES (ACQUIRED/DISPLOSED)");
	
	private String header;
	
	private HeaderEnum(String header) 
	{
		this.header = header;
	}

	public String getHeader() 
	{
		return header;
	}
}
