package com.cams.blaze.response;

import java.util.ArrayList;
import java.util.List;

public class RuleDecision {
	private List<RuleAssociation> ruleAssociation=new ArrayList<RuleAssociation>();
    private Integer index;
    private String rulesetName;
    private String rulesetCode;
    private String rulesetType;
    private String ruleName;
    private String ruleCode;
    private String ruleType;
    private String ruleDecisionResult;
    private String ruleReasonCode;
    private String ruleReasonText;
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

}
