package com.cams.blaze.response;
/**
 * 
 * @author YuHuaPeng
 *
 */
import java.util.ArrayList;
import java.util.List;

public class DecisionFlowHistory {
	/**决策流步骤信息*/
    private List<DecisionFlowStepHistory> decisionFlowStepHistory=new ArrayList<DecisionFlowStepHistory>();
    /**决策流名称*/
    private String flowName;
	public List<DecisionFlowStepHistory> getDecisionFlowStepHistory() {
		return decisionFlowStepHistory;
	}
	public void setDecisionFlowStepHistory(
			List<DecisionFlowStepHistory> decisionFlowStepHistory) {
		this.decisionFlowStepHistory = decisionFlowStepHistory;
	}
	public String getFlowName() {
		return flowName;
	}
	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

}
