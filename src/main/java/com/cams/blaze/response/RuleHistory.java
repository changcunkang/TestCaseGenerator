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
    @Column
    private Long parent_id;

    public Long getParent_id() {
        return parent_id;
    }

    public void setParent_id(Long parent_id) {
        this.parent_id = parent_id;
    }
}
