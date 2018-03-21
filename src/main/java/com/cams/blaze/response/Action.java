package com.cams.blaze.response;

import javax.persistence.*;

/**
 * 
 * @author YuHuaPeng
 *
 */
@Entity
public class Action {
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	@Column
	private Long id;
	@Column(name="strategyDecision_id")
	private	 Long strategyDecision_id;
	public void setStrategyDecision_id(Long strategyDecision_id) {
		this.strategyDecision_id = strategyDecision_id;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	/**行动值域*/
	@Column
	private String actionValue;
	/**行动类型*/
	@Column
	private String actionType;

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public String getActionValue() {
		return actionValue;
	}

	public void setActionValue(String actionValue) {
		this.actionValue = actionValue;
	}
}
