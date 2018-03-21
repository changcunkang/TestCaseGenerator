package com.cams.blaze.response;


import javax.persistence.*;

/**
 * 
 * @author YuHuaPeng
 *
 */
@Entity
public class LimitAction {
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	@Column
	private Long id;
	@Column(name="decisionResponse_id")
	private	 Long decisionResponse_id;
	public void setDecisionResponse_id(Long decisionResponse_id) {
		this.decisionResponse_id = decisionResponse_id;
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
