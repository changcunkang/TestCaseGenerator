
package com.cams.blaze.response;
/**
 * 
 * @author YuHuaPeng
 *
 */
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
public class DecisionFlowStepHistory {
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
	/**规则集历史ʷ*/
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)@JoinColumn(name="parent_id")
	private List<RulesetHistory> rulesetHistory=new ArrayList<RulesetHistory>();
    /**步骤序号*/
    @Column
	private Integer stepIndex;
    /**实施类型*/
    @Column
	private String implementationType;
    /**实施名称*/
    @Column
	private String implementationName;
    /**函数结果*/
    @Lob
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
	@Column
	private Long parent_id;

	public Long getParent_id() {
		return parent_id;
	}

	public void setParent_id(Long parent_id) {
		this.parent_id = parent_id;
	}
    

}
