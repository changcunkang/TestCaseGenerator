package com.fico.testCaseGenerator.testCase;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RIDOInstance {
	
	private String appID;

	private List<TestCaseInstance> ridoResultList = new ArrayList<TestCaseInstance>();
	
	private Map<String, Integer> nodeProcessingMap = null;

	private Map<String, Double> scoreMap = null;

	private String preN60000Node;
	
	private String N60000RuleName;
	
	private String finalResult;
	
	public void setFinalResult(String finalResult) {
		this.finalResult = finalResult;
	}

	public String getFinalResult() {
		return finalResult;
	}

	public String getN60000RuleName() {
		return N60000RuleName;
	}

	public void setN60000RuleName(String n60000RuleName) {
		
		N60000RuleName = n60000RuleName;
	}

	public List<TestCaseInstance> getRidoResultList() {
		return ridoResultList;
	}

	public void setRidoResultList(List<TestCaseInstance> ridoResultList) {
		this.ridoResultList = ridoResultList;
		
		if(ridoResultList != null){
			for(TestCaseInstance ti : ridoResultList){
				if(ti.getFinalResult() != null){
					this.finalResult = ti.getFinalResult();
				}
			}
		}
	}
	
	public String getPreN60000Node() {
		return preN60000Node;
	}

	public void setPreN60000Node(String preN60000Node) {
		this.preN60000Node = preN60000Node;
	}

	public Map<String, Integer> getNodeProcessingMap() {
		return nodeProcessingMap;
	}

	public void setNodeProcessingMap(Map<String, Integer> nodeProcessingMap) {
		this.nodeProcessingMap = nodeProcessingMap;
	}

	public Map<String, Double> getScoreMap() {
		return scoreMap;
	}

	public void setScoreMap(Map<String, Double> scoreMap) {
		this.scoreMap = scoreMap;
	}
	
	public String getAppID() {
		return appID;
	}

	public void setAppID(String appID) {
		this.appID = appID;
	}
}
