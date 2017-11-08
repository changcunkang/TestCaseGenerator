package com.cams.blaze.response;
/**
 * 策略决策信息
 * @author YuHuaPeng
 *
 */
import java.util.ArrayList;
import java.util.List;

public class StrategyDecision {
	
	private List<Limit> limit = new ArrayList<Limit>();
	private List<Action> action = new ArrayList<Action>();
	private List<ReasonCodeSet> reasonCodeSet= new ArrayList<ReasonCodeSet>();
	
	private String strategyEntityLevel;
	private String strategyEntityNumber;
	private String decisionArea;
	private String callType;
	private String SPID;
	private String SPIDdescription;
	private String CCID;
	private String CCIDdescription;
	private String strategyID;
	private String strategyName;
	private String scenarioID;
	private String branchID;
	private String finalReasonCode;
	private Integer tempALvalidDays;
	private String decisionResult;

	public String getStrategyEntityLevel() {
		return strategyEntityLevel;
	}
	public void setStrategyEntityLevel(String strategyEntityLevel) {
		this.strategyEntityLevel = strategyEntityLevel;
	}
	public String getStrategyID() {
		return strategyID;
	}
	public void setStrategyID(String strategyID) {
		this.strategyID = strategyID;
	}
	public String getStrategyName() {
		return strategyName;
	}
	public void setStrategyName(String strategyName) {
		this.strategyName = strategyName;
	}
	public String getDecisionResult() {
		return decisionResult;
	}
	public void setDecisionResult(String decisionResult) {
		this.decisionResult = decisionResult;
	}
	public String getStrategyEntityNumber() {
		return strategyEntityNumber;
	}
	public void setStrategyEntityNumber(String strategyEntityNumber) {
		this.strategyEntityNumber = strategyEntityNumber;
	}
	public List<Limit> getLimit() {
		return limit;
	}
	public void setLimit(List<Limit> limit) {
		this.limit = limit;
	}
	public List<Action> getAction() {
		return action;
	}
	public void setAction(List<Action> action) {
		this.action = action;
	}
	public String getDecisionArea() {
		return decisionArea;
	}
	public void setDecisionArea(String decisionArea) {
		this.decisionArea = decisionArea;
	}
	public String getSPID() {
		return SPID;
	}
	public void setSPID(String sPID) {
		SPID = sPID;
	}
	public String getSPIDdescription() {
		return SPIDdescription;
	}
	public void setSPIDdescription(String sPIDdescription) {
		SPIDdescription = sPIDdescription;
	}
	public String getCCID() {
		return CCID;
	}
	public void setCCID(String cCID) {
		CCID = cCID;
	}
	public String getCCIDdescription() {
		return CCIDdescription;
	}
	public void setCCIDdescription(String cCIDdescription) {
		CCIDdescription = cCIDdescription;
	}
	public String getScenarioID() {
		return scenarioID;
	}
	public void setScenarioID(String scenarioID) {
		this.scenarioID = scenarioID;
	}
	public String getBranchID() {
		return branchID;
	}
	public void setBranchID(String branchID) {
		this.branchID = branchID;
	}
	public Integer getTempALvalidDays() {
		return tempALvalidDays;
	}
	public void setTempALvalidDays(Integer tempALvalidDays) {
		this.tempALvalidDays = tempALvalidDays;
	}
	public String getCallType() {
		return callType;
	}
	public void setCallType(String callType) {
		this.callType = callType;
	}
	public String getFinalReasonCode() {
		return finalReasonCode;
	}
	public void setFinalReasonCode(String finalReasonCode) {
		this.finalReasonCode = finalReasonCode;
	}
	public List<ReasonCodeSet> getReasonCodeSet() {
		return reasonCodeSet;
	}
	public void setReasonCodeSet(List<ReasonCodeSet> reasonCodeSet) {
		this.reasonCodeSet = reasonCodeSet;
	}
}
