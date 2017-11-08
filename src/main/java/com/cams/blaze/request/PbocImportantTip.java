package com.cams.blaze.request;

import java.util.Date;

public class PbocImportantTip {
	private Date tipDate;
	private String tipText;
	public Date getTipDate() {
		return tipDate;
	}
	public void setTipDate(Date tipDate) {
		this.tipDate = tipDate;
	}
	public String getTipText() {
		return tipText;
	}
	public void setTipText(String tipText) {
		this.tipText = tipText;
	}
}
