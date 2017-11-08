package com.cams.blaze.response;
/**
 * @author YuHuaPeng
 *
 */
import java.util.*;

public class ProcessingHistory {
    private DecisionFlowHistory decisionFlowHistory=new DecisionFlowHistory();
	private String entryPoint;
    private Date startTimestamp;
    private Date stopTimestamp;
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
