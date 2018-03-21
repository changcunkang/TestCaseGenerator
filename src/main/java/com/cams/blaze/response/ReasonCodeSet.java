package com.cams.blaze.response;

import javax.persistence.*;

@Entity
public class ReasonCodeSet {
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
	@Column
	private String reasonCode;
	@Column
	private String reasonText;
	public String getReasonCode() {
		return reasonCode;
	}
	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}
	public String getReasonText() {
		return reasonText;
	}
	public void setReasonText(String reasonText) {
		this.reasonText = reasonText;
	}

}
