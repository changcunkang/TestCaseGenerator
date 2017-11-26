package com.cams.blaze.response;
/**
 * @author YuHuaPeng
 *
 */
import javax.persistence.*;
import java.util.*;
@Entity
public class ProcessingHistory {
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
	@OneToOne(cascade = CascadeType.ALL)
	private DecisionFlowHistory decisionFlowHistory=new DecisionFlowHistory();
	@Column
	private String entryPoint;
    @Column
	private Date startTimestamp;
    @Column
	private Date stopTimestamp;
    @Column
	private Integer elapsedMillis;

	public DecisionFlowHistory getDecisionFlowHistory() {
		return decisionFlowHistory;
	}
	public void setDecisionFlowHistory(DecisionFlowHistory decisionFlowHistory) {
		this.decisionFlowHistory = decisionFlowHistory;
	}
	public Date getStartTimestamp() {
		return startTimestamp;
	}
	public void setStartTimestamp(Date startTimestamp) {
		this.startTimestamp = startTimestamp;
	}
	public Date getStopTimestamp() {
		return stopTimestamp;
	}
	public void setStopTimestamp(Date stopTimestamp) {
		this.stopTimestamp = stopTimestamp;
	}
	public Integer getElapsedMillis() {
		return elapsedMillis;
	}
	public void setElapsedMillis(Integer elapsedMillis) {
		this.elapsedMillis = elapsedMillis;
	}
	public String getEntryPoint() {
		return entryPoint;
	}
	public void setEntryPoint(String entryPoint) {
		this.entryPoint = entryPoint;
	}
}
