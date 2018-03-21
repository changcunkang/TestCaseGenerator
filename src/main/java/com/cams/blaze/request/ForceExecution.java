package com.cams.blaze.request;

import javax.persistence.*;
import java.util.*;

@Entity
public class ForceExecution {
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
	private String court;
	@Column
	private String caseReason;
	@Column
	private Date registerDate;
	@Column
	private String closedType;
	@Column
	private String caseState;
	@Column
	private Date closedDate;
	@Column
	private String enforceObject;
	@Column
	private Double enforceObjectMoney;
	@Column
	private String alreadyEnforceObject;
	@Column
	private Double alreadyEnforceObjectMoney;
	public String getCourt() {
		return court;
	}
	public void setCourt(String court) {
		this.court = court;
	}
	public String getCaseReason() {
		return caseReason;
	}
	public void setCaseReason(String caseReason) {
		this.caseReason = caseReason;
	}
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	public String getClosedType() {
		return closedType;
	}
	public void setClosedType(String closedType) {
		this.closedType = closedType;
	}
	public String getCaseState() {
		return caseState;
	}
	public void setCaseState(String caseState) {
		this.caseState = caseState;
	}
	public Date getClosedDate() {
		return closedDate;
	}
	public void setClosedDate(Date closedDate) {
		this.closedDate = closedDate;
	}
	public String getEnforceObject() {
		return enforceObject;
	}
	public void setEnforceObject(String enforceObject) {
		this.enforceObject = enforceObject;
	}
	public Double getEnforceObjectMoney() {
		return enforceObjectMoney;
	}
	public void setEnforceObjectMoney(Double enforceObjectMoney) {
		this.enforceObjectMoney = enforceObjectMoney;
	}
	public String getAlreadyEnforceObject() {
		return alreadyEnforceObject;
	}
	public void setAlreadyEnforceObject(String alreadyEnforceObject) {
		this.alreadyEnforceObject = alreadyEnforceObject;
	}
	public Double getAlreadyEnforceObjectMoney() {
		return alreadyEnforceObjectMoney;
	}
	public void setAlreadyEnforceObjectMoney(Double alreadyEnforceObjectMoney) {
		this.alreadyEnforceObjectMoney = alreadyEnforceObjectMoney;
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
