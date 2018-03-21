package com.cams.blaze.response;

import javax.persistence.*;

/**
 * 
 * @author YuHuaPeng
 *
 */
@Entity
public class RuleHistory {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    @Column
    private Long id;
    @Column(name="rulesetHistory_id")
    private	 Long rulesetHistory_id;
    public void setRulesetHistory_id(Long rulesetHistory_id) {
        this.rulesetHistory_id = rulesetHistory_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Column
	private String ruleName;

    @Column
	private String ruleCode;
    public String getRuleName() {
        return ruleName;
    }
    public void setRuleName(String value) {
        this.ruleName = value;
    }
    public String getRuleCode() {
        return ruleCode;
    }
    public void setRuleCode(String value) {
        this.ruleCode = value;
    }

}
