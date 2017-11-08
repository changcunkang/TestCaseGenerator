package com.cams.blaze.request;

public class OverdueRecord {
	private String month;
	private Integer lastMonths;
	private Double amount;
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public Integer getLastMonths() {
		return lastMonths;
	}
	public void setLastMonths(Integer lastMonths) {
		this.lastMonths = lastMonths;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}


}
