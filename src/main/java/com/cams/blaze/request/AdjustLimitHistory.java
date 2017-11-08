package com.cams.blaze.request;

import java.util.Date;

public class AdjustLimitHistory {
	private Date adjustLimitDate;
	private Double adjustLimitAmtBefore;
	private Double adjustLimitAmtAfter;
	private String adjustLimitType;
	private Date tempALunValidDate;

	
	public Date getTempALunValidDate() {
		return tempALunValidDate;
	}
	public void setTempALunValidDate(Date tempALunValidDate) {
		this.tempALunValidDate = tempALunValidDate;
	}
	public Date getAdjustLimitDate() {
		return adjustLimitDate;
	}
	public void setAdjustLimitDate(Date adjustLimitDate) {
		this.adjustLimitDate = adjustLimitDate;
	}
	public Double getAdjustLimitAmtBefore() {
		return adjustLimitAmtBefore;
	}
	public void setAdjustLimitAmtBefore(Double adjustLimitAmtBefore) {
		this.adjustLimitAmtBefore = adjustLimitAmtBefore;
	}
	public Double getAdjustLimitAmtAfter() {
		return adjustLimitAmtAfter;
	}
	public void setAdjustLimitAmtAfter(Double adjustLimitAmtAfter) {
		this.adjustLimitAmtAfter = adjustLimitAmtAfter;
	}
	public String getAdjustLimitType() {
		return adjustLimitType;
	}
	public void setAdjustLimitType(String adjustLimitType) {
		this.adjustLimitType = adjustLimitType;
	}
	
	

}
