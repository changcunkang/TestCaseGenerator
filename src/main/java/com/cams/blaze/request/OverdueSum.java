package com.cams.blaze.request;

public class OverdueSum {
	private String overdueCD;
	private Integer count;
	private Integer months;
	private Double highestOverdueAmountPerMon;
	private Integer maxDuration;
	public String getOverdueCD() {
		return overdueCD;
	}
	public void setOverdueCD(String overdueCD) {
		this.overdueCD = overdueCD;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getMonths() {
		return months;
	}
	public void setMonths(Integer months) {
		this.months = months;
	}
	public Double getHighestOverdueAmountPerMon() {
		return highestOverdueAmountPerMon;
	}
	public void setHighestOverdueAmountPerMon(Double highestOverdueAmountPerMon) {
		this.highestOverdueAmountPerMon = highestOverdueAmountPerMon;
	}
	public Integer getMaxDuration() {
		return maxDuration;
	}
	public void setMaxDuration(Integer maxDuration) {
		this.maxDuration = maxDuration;
	}


}
