package com.fico.testCaseGenerator.data.configuration;

import java.util.ArrayList;
import java.util.List;

public class Item {

	private long id;
	
	private long restrictionID;
	
	private Double percentage;
	
	private long resultMaxNum;

	private String minExpression;

	private String maxExpression;

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
			return true;
		}
	}
	
	public Item() {
	}

	public Item(String value, double percentage) {

		this.percentage = percentage;
	}

	public Double getPercentage() {
		return percentage;
	}

	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}


	public String getMinExpression() {
		return minExpression;
	}

	public void setMinExpression(String minExpression) {
		this.minExpression = minExpression;
	}

	public String getMaxExpression() {
		return maxExpression;
	}

	public void setMaxExpression(String maxExpression) {
		this.maxExpression = maxExpression;
	}

}
