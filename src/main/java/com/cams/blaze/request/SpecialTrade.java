package com.cams.blaze.request;

import java.util.Date;

public class SpecialTrade {
	private String type;
	private Date getTime;
	private Integer changingMonths;
	private Double changingAmount;
	private String content;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getGetTime() {
		return getTime;
	}
	public void setGetTime(Date getTime) {
		this.getTime = getTime;
	}
	public Integer getChangingMonths() {
		return changingMonths;
	}
	public void setChangingMonths(Integer changingMonths) {
		this.changingMonths = changingMonths;
	}
	public Double getChangingAmount() {
		return changingAmount;
	}
	public void setChangingAmount(Double changingAmount) {
		this.changingAmount = changingAmount;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}


}
