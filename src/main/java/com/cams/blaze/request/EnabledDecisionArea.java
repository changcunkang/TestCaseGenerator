package com.cams.blaze.request;

import javax.persistence.*;

@Entity
public class EnabledDecisionArea {

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
	private String decisionArea;

	public String getDecisionArea() {
		return decisionArea;
	}

	public void setDecisionArea(String decisionArea) {
		this.decisionArea = decisionArea;
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
