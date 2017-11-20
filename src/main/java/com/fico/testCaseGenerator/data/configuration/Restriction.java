package com.fico.testCaseGenerator.data.configuration;

import java.util.List;

public class Restriction {

	public static final String TYPE_SECTION = "section";

	public static final String TYPE_DEPENDENT = "dependent";

	public static final String TYPE_FUNCTION = "function";

	public static final String TYPE_FILTER = "filter";
	
	private long id;

	private Extendtion extendtion;
	
	private String type;
	
	private double nullPercentage;

	private Double MaxNum;

	private String minStr;
	
	public List<Item> item;
	
	private double transfersType;
	
	public Restriction() {
	}

	public Restriction(String type, double nullPercentage, List<Item> item) {
		this.type = type;
		this.nullPercentage = nullPercentage;
		this.item = item;

	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getNullPercentage() {
		return nullPercentage;
	}

	public void setNullPercentage(double nullPercentage) {
		this.nullPercentage = nullPercentage;
	}

	public List<Item> getItem() {
		return item;
	}

	public void setItem(List<Item> item) {
		this.item = item;
	}

	public double getTransfersType() {
		return transfersType;
	}

	public void setTransfersType(double transfersType) {
		this.transfersType = transfersType;
	}

	public String getMinStr() {
		
		if( minStr == null ){
			return "0";
		}
		
		return minStr;
	}

	public void setMinStr(String minStr) {
		this.minStr = minStr;
	}

	public Double getMaxNum() {
		return MaxNum;
	}

	public void setMaxNum(Double maxNum) {
		MaxNum = maxNum;
	}
	
	public int getTestCaseMinNum(){
		
		if(this.getMinStr() == null || "".equals(this.getMinStr())){
			return 0;
		}
		
		return new Double(this.getMinStr()).intValue();
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Extendtion getExtendtion() {
		return extendtion;
	}

	public void setExtendtion(Extendtion extendtion) {
		this.extendtion = extendtion;
	}
}
