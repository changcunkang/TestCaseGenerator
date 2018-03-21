package com.cams.blaze.response;

import javax.persistence.*;

@Entity
public class RuleAssociation {
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	@Column
	private Long id;



	@Column(name="ruleDecision_id")
	private	 Long ruleDecision_id;

	public void setRuleDecision_id(Long ruleDecision_id) {
		this.ruleDecision_id = ruleDecision_id;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@Column
	private String associationID;

	public String getAssociationID() {
		return associationID;
	}

	public void setAssociationID(String associationID) {
		this.associationID = associationID;
	}
}
