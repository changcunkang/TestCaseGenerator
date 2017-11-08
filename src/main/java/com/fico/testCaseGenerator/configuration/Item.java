package com.fico.testCaseGenerator.configuration;

import java.util.ArrayList;
import java.util.List;

public class Item {

	private long id;
	
	private long restrictionID;
	
	private String value;
	
	private Double percentage;
	
	private long resultMaxNum;
	
	private long cnt = 0;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getRestrictionID() {
		return restrictionID;
	}

	public void setRestrictionID(long restrictionID) {
		this.restrictionID = restrictionID;
	}


	private List<String> resultIdList;

	public List<String> getResultIdList() {
		
		if( this.resultIdList == null ){
			this.resultIdList = new ArrayList<String>();
		}
		
		return this.resultIdList;
	}

	public long getResultMaxNum() {
		return resultMaxNum;
	}

	public void setResultMaxNum(long resultMaxNum) {
		this.resultMaxNum = resultMaxNum;
	}

	public long getGenerated(){
		return cnt;
	}
	
	public boolean addResultID(String resultId){
		if(cnt == resultMaxNum){
			return false;
		}else{
			cnt++;
			if(cnt%100 ==0){
				System.out.println( "Add " + this.getValue() + " current is" + cnt +" in total of " + resultMaxNum);
			}
			return true;
		}
	}
	
	public Item() {
	}

	public Item(String value, double percentage) {
		this.value = value;
		this.percentage = percentage;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Double getPercentage() {
		return percentage;
	}

	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}
}
