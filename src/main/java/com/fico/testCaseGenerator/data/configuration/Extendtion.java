package com.fico.testCaseGenerator.data.configuration;

import com.fico.testCaseGenerator.data.AbstractTestData;

public class Extendtion {

	private long id;
	
	private long projectID;

	private AbstractTestData parentTestData;

	public AbstractTestData getParentTestData() {
		return parentTestData;
	}

	public void setParentTestData(AbstractTestData parentTestData) {
		this.parentTestData = parentTestData;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getProjectID() {
		return projectID;
	}

	public void setProjectID(long projectID) {
		this.projectID = projectID;
	}

	private String name;
	private Restriction restriction;
	private String realPath;

	public long getSimpleFieldID() {
		return simpleFieldID;
	}

	public void setSimpleFieldID(long simpleFieldID) {
		this.simpleFieldID = simpleFieldID;
	}

	private long simpleFieldID;


	public Extendtion() {
	}

	public Extendtion(String name, Restriction restriction) {
		this.name = name;
		this.restriction = restriction;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Restriction getRestriction() {
		return restriction;
	}

	public void setRestriction( Restriction restriction) {
		this.restriction = restriction;
	}

	public String getRealPath() {
		return realPath;
	}

	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}

	public String toString(){
		return this.realPath;
	}
}
