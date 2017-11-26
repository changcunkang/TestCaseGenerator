package com.cams.blaze.request;

import javax.persistence.*;

@Entity
public class EnabledDecisionArea {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@Column
	private String decisionArea;

	public String getDecisionArea() {
		return decisionArea;
	}

	public void setDecisionArea(String decisionArea) {
		this.decisionArea = decisionArea;
	}
}
