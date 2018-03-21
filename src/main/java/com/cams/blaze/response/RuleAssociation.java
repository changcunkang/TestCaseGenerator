package com.cams.blaze.response;

import javax.persistence.*;

@Entity
public class RuleAssociation {
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
	private String associationID;

	public String getAssociationID() {
		return associationID;
	}

	public void setAssociationID(String associationID) {
		this.associationID = associationID;
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
