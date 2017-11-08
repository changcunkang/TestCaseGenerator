package com.cams.blaze.response;
/**
 * ������ʷ��Ϣ
 * @author YuHuaPeng
 *
 */
import java.util.ArrayList;
import java.util.List;



public class RulesetHistory {

    private List<RuleHistory> ruleHistory=new ArrayList<RuleHistory>();

    private String rulesetName;

    private String rulesetCode;

    public List<RuleHistory> getRuleHistory() {
		return ruleHistory;
	}
	public void setRuleHistory(List<RuleHistory> ruleHistory) {
		this.ruleHistory = ruleHistory;
	}
	public String getRulesetName() {
        return rulesetName;
    }
    public void setRulesetName(String value) {
        this.rulesetName = value;
    }
    public String getRulesetCode() {
        return rulesetCode;
    }
    public void setRulesetCode(String value) {
        this.rulesetCode = value;
    }

}
