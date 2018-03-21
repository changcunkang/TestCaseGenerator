package com.cams.blaze.response;

import javax.persistence.*;

@Entity(name="limit_blaze")
public class Limit {
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	@Column
	private Long id;
	@Column(name="strategyDecision_id")
	private	 Long strategyDecision_id;
	public void setStrategyDecision_id(Long strategyDecision_id) {
		this.strategyDecision_id = strategyDecision_id;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	/**行动值域*/
	@Column
	private String limitItemID;
	@Column
	private Double limitItemValue;
	public Double getLimitItemValue() {
		return limitItemValue;
	}
	public void setLimitItemValue(Double limitItemValue) {
		this.limitItemValue = limitItemValue;
	}
	public String getLimitItemID() {
		return limitItemID;
	}
	public void setLimitItemID(String limitItemID) {
		this.limitItemID = limitItemID;
	}
}
