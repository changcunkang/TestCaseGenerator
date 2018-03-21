package com.cams.blaze.response;
/**
 * 策略决策信息
 * @author YuHuaPeng
 *
 */
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
public class StrategyDecision {


	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)

	@Column(name="decisionResponse_id")
	private	 Long decisionResponse_id;
	public void setDecisionResponse_id(Long decisionResponse_id) {
		this.decisionResponse_id = decisionResponse_id;
	}
	@Column
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumns(
			@JoinColumn(name = "limit_id", referencedColumnName = "id")
	)
	private List<Limit> limit = new ArrayList<Limit>();
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Action> action = new ArrayList<Action>();
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<ReasonCodeSet> reasonCodeSet= new ArrayList<ReasonCodeSet>();
	
	@Column
	private String strategyEntityLevel;
	@Column
	private String strategyEntityNumber;
	@Column
	private String decisionArea;
	@Column
	private String callType;
	@Column
	private String SPID;
	@Column
	private String SPIDdescription;
	@Column
	private String CCID;
	@Column
	private String CCIDdescription;
	@Column
	private String strategyID;
	@Column
	private String strategyName;
	@Column
	private String scenarioID;
	@Column
	private String branchID;
	@Column
	private String finalReasonCode;
	@Column
	private Integer tempALvalidDays;
	@Column
	private String decisionResult;
	@Column
	private String riskSegment1;
	@Column
	private String riskSegment2;
	@Column
	private String riskSegment3;
	@Column
	private String riskSegment4;
	@Column
	private String riskSegment5;

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

	public String getRiskSegment1() {
		return riskSegment1;
	}

	public void setRiskSegment1(String riskSegment1) {
		this.riskSegment1 = riskSegment1;
	}

	public String getRiskSegment2() {
		return riskSegment2;
	}

	public void setRiskSegment2(String riskSegment2) {
		this.riskSegment2 = riskSegment2;
	}

	public String getRiskSegment3() {
		return riskSegment3;
	}

	public void setRiskSegment3(String riskSegment3) {
		this.riskSegment3 = riskSegment3;
	}

	public String getRiskSegment4() {
		return riskSegment4;
	}

	public void setRiskSegment4(String riskSegment4) {
		this.riskSegment4 = riskSegment4;
	}

	public String getRiskSegment5() {
		return riskSegment5;
	}

	public void setRiskSegment5(String riskSegment5) {
		this.riskSegment5 = riskSegment5;
	}
}
