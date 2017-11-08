package com.fico.testCaseGenerator;

import com.fico.testCaseGenerator.configuration.Extendtion;

public abstract class AbstractTestData {

	private Extendtion extendtion;

	private Object dataStructureElement;
	
	private Object testCaseInstance;
	
	protected String name;
	
	private Long projectID;
	
	private Long id;

	public static final String PATH_SEPARATOR = "/";
	
	public static final String GLOABLE_PATH_PREFIX = PATH_SEPARATOR +  "Projects" + PATH_SEPARATOR;
	
	public boolean isGloableDependency(){
		if(this.getExtendtion() != null && this.getExtendtion().getDependency() != null && this.getExtendtion().getDependency().getParentPath() != null){
			
			String parentPath = this.getExtendtion().getDependency().getParentPath();
			
			if(parentPath.startsWith(GLOABLE_PATH_PREFIX)){
				return true;
			}
		}
		return false;
	}
	
	private boolean generateTestCaseFinish = false;
	
	public boolean isGenerateTestCaseFinish() {
		return generateTestCaseFinish;
	}

	public void setGenerateTestCaseFinish(boolean generateTestCaseFinish) {
		this.generateTestCaseFinish = generateTestCaseFinish;
	}
	
	public Object getTestCaseInstance() {
		return testCaseInstance;
	}

	public void setTestCaseInstance(Object testCaseInstance) {
		this.testCaseInstance = testCaseInstance;
	}

	public Object getDataStructureElement() {
		return dataStructureElement;
	}

	public void setDataStructureElement(Object dataStructureElement) {
		this.dataStructureElement = dataStructureElement;
	}

	public Extendtion getExtendtion() {
		return extendtion;
	}

	public void setExtendtion(Extendtion extendtion) {
		this.extendtion = extendtion;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
}
