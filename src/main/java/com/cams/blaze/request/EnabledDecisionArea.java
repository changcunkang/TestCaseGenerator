package com.cams.blaze.request;

import javax.persistence.*;

@Entity
public class EnabledDecisionArea {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column
	private String decisionArea;

	public String getDecisionArea() {
		return decisionArea;
	}

	public void setDecisionArea(String decisionArea) {
		this.decisionArea = decisionArea;
	}
}
