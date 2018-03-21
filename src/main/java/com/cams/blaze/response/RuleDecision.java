package com.cams.blaze.response;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
public class RuleDecision {
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
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)@JoinColumn(name="parent_id")
	private List<RuleAssociation> ruleAssociation=new ArrayList<RuleAssociation>();
	@Column(name = "index_num")
	private Integer index;
    @Column
	private String rulesetName;
    @Column
	private String rulesetCode;
    @Column
	private String rulesetType;
    @Column
	private String ruleName;
    @Column
	private String ruleCode;
    @Column
	private String ruleType;
    @Column
	private String ruleDecisionResult;
    @Column
	private String ruleReasonCode;
    @Column
	private String ruleReasonText;
    @Column
	private Integer ruleSeverityLevel;
    
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	public String getRulesetName() {
		return rulesetName;
	}
	public void setRulesetName(String rulesetName) {
		this.rulesetName = rulesetName;
	}
	public String getRulesetCode() {
		return rulesetCode;
	}
	public void setRulesetCode(String rulesetCode) {
		this.rulesetCode = rulesetCode;
	}
	public String getRuleCode() {
		return ruleCode;
	}
	public void setRuleCode(String ruleCode) {
		this.ruleCode = ruleCode;
	}
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	public String getRuleDecisionResult() {
		return ruleDecisionResult;
	}
	public void setRuleDecisionResult(String ruleDecisionResult) {
		this.ruleDecisionResult = ruleDecisionResult;
	}
	
	public String getRuleReasonCode() {
		return ruleReasonCode;
	}
	public void setRuleReasonCode(String ruleReasonCode) {
		this.ruleReasonCode = ruleReasonCode;
	}
	public String getRuleReasonText() {
		return ruleReasonText;
	}
	public void setRuleReasonText(String ruleReasonText) {
		this.ruleReasonText = ruleReasonText;
	}
	public Integer getRuleSeverityLevel() {
		return ruleSeverityLevel;
	}
	public void setRuleSeverityLevel(Integer ruleSeverityLevel) {
		this.ruleSeverityLevel = ruleSeverityLevel;
	}
	public String getRuleType() {
		return ruleType;
	}
	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}
	public List<RuleAssociation> getRuleAssociation() {
		return ruleAssociation;
	}
	public void setRuleAssociation(List<RuleAssociation> ruleAssociation) {
		this.ruleAssociation = ruleAssociation;
	}
	public String getRulesetType() {
		return rulesetType;
	}
	public void setRulesetType(String rulesetType) {
		this.rulesetType = rulesetType;
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
