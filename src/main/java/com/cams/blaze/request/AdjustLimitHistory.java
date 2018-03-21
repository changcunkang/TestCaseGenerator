package com.cams.blaze.request;

import javax.persistence.*;
import java.util.Date;

@Entity
public class AdjustLimitHistory {

	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@Column
	private Date adjustLimitDate;
	@Column
	private Double adjustLimitAmtBefore;
	@Column
	private Double adjustLimitAmtAfter;
	@Column
	private String adjustLimitType;
	@Column
	private Date tempALunValidDate;
	@Column
	private String adjustLimitDirection;



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


	public String getAdjustLimitDirection() {
		return adjustLimitDirection;
	}

	public void setAdjustLimitDirection(String adjustLimitDirection) {
		this.adjustLimitDirection = adjustLimitDirection;
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
