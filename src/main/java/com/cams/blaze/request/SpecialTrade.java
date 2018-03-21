package com.cams.blaze.request;

import javax.persistence.*;
import java.util.Date;
@Entity
public class SpecialTrade {
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	@Column
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@Column
	private String type;
	@Column
	private Date getTime;
	@Column
	private Integer changingMonths;
	@Column
	private Double changingAmount;
	@Column
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

	@Column
	private Long parent_id;

	public Long getParent_id() {
		return parent_id;
	}

	public void setParent_id(Long parent_id) {
		this.parent_id = parent_id;
	}
}
