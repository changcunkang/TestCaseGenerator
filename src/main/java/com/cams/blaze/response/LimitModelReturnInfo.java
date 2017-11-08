package com.cams.blaze.response;

import java.util.ArrayList;
import java.util.List;

public class LimitModelReturnInfo {
	private List<LimitItem> limitItem = new ArrayList<LimitItem>();
	private List<LimitAction> limitAction = new ArrayList<LimitAction>();
	private String associationID;
	private String limitModelName;
	private String limitModelID;
	public String getAssociationID() {
		return associationID;
	}
	public void setAssociationID(String associationID) {
		this.associationID = associationID;
	}
	public String getLimitModelName() {
		return limitModelName;
	}
	public void setLimitModelName(String limitModelName) {
		this.limitModelName = limitModelName;
	}
	public String getLimitModelID() {
		return limitModelID;
	}
	public void setLimitModelID(String limitModelID) {
		this.limitModelID = limitModelID;
	}
	public List<LimitItem> getLimitItem() {
		return limitItem;
	}
	public void setLimitItem(List<LimitItem> limitItem) {
		this.limitItem = limitItem;
	}
	public List<LimitAction> getLimitAction() {
		return limitAction;
	}
	public void setLimitAction(List<LimitAction> limitAction) {
		this.limitAction = limitAction;
	}
}
