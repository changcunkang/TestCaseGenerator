package com.cams.blaze.response;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
public class LimitModelReturnInfo {
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	@Column
	private Long id;
	@Column(name="decisionResponse_id")
	private	 Long decisionResponse_id;
	public void setDecisionResponse_id(Long decisionResponse_id) {
		this.decisionResponse_id = decisionResponse_id;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<LimitItem> limitItem = new ArrayList<LimitItem>();
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<LimitAction> limitAction = new ArrayList<LimitAction>();
	@Column
	private String associationID;
	@Column
	private String limitModelName;
	@Column
	private String limitModelID;
	public String getAssociationID() {
		return associationID;
	}
	public void setAssociationID(String associationID) {
		this.associationID = associationID;
	}
	public String getLimitModelName() {
		return limitModelName;
	}
	public void setLimitModelName(String limitModelName) {
		this.limitModelName = limitModelName;
	}
	public String getLimitModelID() {
		return limitModelID;
	}
	public void setLimitModelID(String limitModelID) {
		this.limitModelID = limitModelID;
	}
	public List<LimitItem> getLimitItem() {
		return limitItem;
	}
	public void setLimitItem(List<LimitItem> limitItem) {
		this.limitItem = limitItem;
	}
	public List<LimitAction> getLimitAction() {
		return limitAction;
	}
	public void setLimitAction(List<LimitAction> limitAction) {
		this.limitAction = limitAction;
	}
}
