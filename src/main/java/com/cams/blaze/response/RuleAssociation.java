package com.cams.blaze.response;

import javax.persistence.*;

@Entity
public class RuleAssociation {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
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
}
