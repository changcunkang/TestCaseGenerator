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
public class DecisionFlowHistory {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	/**决策流步骤信息*/
	@OneToMany(cascade = CascadeType.ALL)
	private List<DecisionFlowStepHistory> decisionFlowStepHistory=new ArrayList<DecisionFlowStepHistory>();
    /**决策流名称*/
    @Column
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
