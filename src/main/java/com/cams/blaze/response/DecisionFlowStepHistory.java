
package com.cams.blaze.response;
/**
 * 
 * @author YuHuaPeng
 *
 */
import java.util.ArrayList;
import java.util.List;

public class DecisionFlowStepHistory {
	/**规则集历史ʷ*/
    private List<RulesetHistory> rulesetHistory=new ArrayList<RulesetHistory>();
    /**步骤序号*/
    private Integer stepIndex;
    /**实施类型*/
    private String implementationType;
    /**实施名称*/
    private String implementationName;
    /**函数结果*/
    private String functionResult;

	public List<RulesetHistory> getRulesetHistory() {
		return rulesetHistory;
	}
	public void setRulesetHistory(List<RulesetHistory> rulesetHistory) {
		this.rulesetHistory = rulesetHistory;
	}
	public Integer getStepIndex() {
		return stepIndex;
	}
	public void setStepIndex(Integer stepIndex) {
		this.stepIndex = stepIndex;
	}
	public String getImplementationType() {
		return implementationType;
	}
	public void setImplementationType(String implementationType) {
		this.implementationType = implementationType;
	}
	public String getImplementationName() {
		return implementationName;
	}
	public void setImplementationName(String implementationName) {
		this.implementationName = implementationName;
	}
	public String getFunctionResult() {
		return functionResult;
	}
	public void setFunctionResult(String functionResult) {
		this.functionResult = functionResult;
	}

    

}
