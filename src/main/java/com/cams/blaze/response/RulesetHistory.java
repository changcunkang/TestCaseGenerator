package com.cams.blaze.response;
/**
 * ������ʷ��Ϣ
 * @author YuHuaPeng
 *
 */
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class RulesetHistory {
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
	private List<RuleHistory> ruleHistory=new ArrayList<RuleHistory>();

    @Column
	private String rulesetName;

    @Column
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
    @Column
    private Long parent_id;

    public Long getParent_id() {
        return parent_id;
    }

    public void setParent_id(Long parent_id) {
        this.parent_id = parent_id;
    }
}
