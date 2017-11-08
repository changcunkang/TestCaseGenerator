package com.fico.testCaseGenerator.configuration;

public class Dependency {

	public static int TYPE_NULL = 0;
	public static int TYPE_NUMBER = 1;
	public static int TYPE_EQUICALENCE = 2;
	public static int TYPE_FUNCTIONNAME = 3;

	private long id;
	
	private long projectID;
	private long extendtionID;
	public long getExtendtionID() {
		return extendtionID;
	}

	public void setExtendtionID(long extendtionID) {
		this.extendtionID = extendtionID;
	}

	private int type;
	private String functionName;
	private String parentPath;

	public Dependency() {
	}

	public Dependency(int type, String functionName, String parentPath) {
		this.type = type;
		this.functionName = functionName;
		this.parentPath = parentPath;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getParentPath() {
		return parentPath;
	}

	public void setParentPath(String parentPath) {
		this.parentPath = parentPath;
	}

	public static int getTYPE_NULL() {
		return TYPE_NULL;
	}

	public static void setTYPE_NULL(int tYPE_NULL) {
		TYPE_NULL = tYPE_NULL;
	}

	public static int getTYPE_NUMBER() {
		return TYPE_NUMBER;
	}

	public static void setTYPE_NUMBER(int tYPE_NUMBER) {
		TYPE_NUMBER = tYPE_NUMBER;
	}

	public static int getTYPE_EQUICALENCE() {
		return TYPE_EQUICALENCE;
	}

	public static void setTYPE_EQUICALENCE(int tYPE_EQUICALENCE) {
		TYPE_EQUICALENCE = tYPE_EQUICALENCE;
	}

	public static int getTYPE_FUNCTIONNAME() {
		return TYPE_FUNCTIONNAME;
	}

	public static void setTYPE_FUNCTIONNAME(int tYPE_FUNCTIONNAME) {
		TYPE_FUNCTIONNAME = tYPE_FUNCTIONNAME;
	}

	public long getProjectID() {
		return projectID;
	}

	public void setProjectID(long projectID) {
		this.projectID = projectID;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
