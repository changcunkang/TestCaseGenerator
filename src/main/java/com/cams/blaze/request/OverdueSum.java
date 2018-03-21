package com.cams.blaze.request;

import javax.persistence.*;

@Entity
public class OverdueSum {
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
	private String overdueCD;
	@Column
	private Integer count;
	@Column
	private Integer months;
	@Column
	private Double highestOverdueAmountPerMon;
	@Column
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

	@Column
	private Long parent_id;

	public Long getParent_id() {
		return parent_id;
	}

	public void setParent_id(Long parent_id) {
		this.parent_id = parent_id;
	}
}
