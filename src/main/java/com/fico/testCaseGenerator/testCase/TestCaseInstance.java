package com.fico.testCaseGenerator.testCase;

import java.util.HashMap;
import java.util.Map;

public class TestCaseInstance {

	private Long id;

	private Object srcInput;

	private Long projectID;
	
	private String isInput;
	
	private String appID;
	
	private String content;

	private String finalOutput;
	
	private String finalResult;

	public String getFinalResult() {
		return finalResult;
	}

	public void setFinalResult(String finalResult) {
		this.finalResult = finalResult;
	}

	public String getFinalOutput() {
		return finalOutput;
	}

	public void setFinalOutput(String finalOutput) {
		this.finalOutput = finalOutput;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProjectID() {
		return projectID;
	}

	public void setProjectID(Long projectID) {
		this.projectID = projectID;
	}

	public String getIsInput() {
		return isInput;
	}

	public void setIsInput(String isInput) {
		this.isInput = isInput;
	}

	public String getAppID() {
		return appID;
	}

	public void setAppID(String appID) {
		this.appID = appID;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Object getSrcInput() {
		return srcInput;
	}

	public void setSrcInput(Object srcInput) {
		this.srcInput = srcInput;
	}
	
}
