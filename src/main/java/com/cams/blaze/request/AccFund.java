package com.cams.blaze.request;

import java.util.*;

import org.springframework.beans.BeanUtils;

public class AccFund {
	private String area;
	private Date registerDate;
	private String firstMonth;
	private String toMonth;
	private String state;
	private Double pay;
	private Double ownPercent;
	private Double comPercent;
	private String organname;
	private Date getTime;
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	public String getFirstMonth() {
		return firstMonth;
	}
	public void setFirstMonth(String firstMonth) {
		this.firstMonth = firstMonth;
	}
	public String getToMonth() {
		return toMonth;
	}
	public void setToMonth(String toMonth) {
		this.toMonth = toMonth;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Double getPay() {
		return pay;
	}
	public void setPay(Double pay) {
		this.pay = pay;
	}
	public Double getOwnPercent() {
		return ownPercent;
	}
	public void setOwnPercent(Double ownPercent) {
		this.ownPercent = ownPercent;
	}
	public Double getComPercent() {
		return comPercent;
	}
	public void setComPercent(Double comPercent) {
		this.comPercent = comPercent;
	}
	public String getOrganname() {
		return organname;
	}
	public void setOrganname(String organname) {
		this.organname = organname;
	}
	public Date getGetTime() {
		return getTime;
	}
	public void setGetTime(Date getTime) {
		this.getTime = getTime;
	}


}
