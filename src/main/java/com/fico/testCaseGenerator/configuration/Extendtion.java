package com.fico.testCaseGenerator.configuration;

public class Extendtion {

	private long id;
	
	private long projectID;
	

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
	private Dependency dependency;
	private Restriction restriction;
	private String realPath;
	public long getTestDataID() {
		return testDataID;
	}

	public void setTestDataID(long testDataID) {
		this.testDataID = testDataID;
	}

	public long getSimpleFieldID() {
		return simpleFieldID;
	}

	public void setSimpleFieldID(long simpleFieldID) {
		this.simpleFieldID = simpleFieldID;
	}

	private long testDataID;
	private long simpleFieldID;



	public Extendtion() {
	}

	public Extendtion(String name, Dependency dependency,
			Restriction restriction) {
		this.name = name;
		this.dependency = dependency;
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
	

	public Dependency getDependency() {
		return dependency;
	}

	public void setDependency(Dependency dependency) {
		this.dependency = dependency;
	}

	public String toString(){
		return this.realPath;
	}
}
