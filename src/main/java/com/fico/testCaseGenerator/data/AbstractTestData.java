package com.fico.testCaseGenerator.data;

import com.fico.testCaseGenerator.data.configuration.Extendtion;
import com.fico.testCaseGenerator.data.configuration.Item;
import com.fico.testCaseGenerator.util.TestCaseUtils;

import java.util.*;

public abstract class AbstractTestData {

	private Extendtion extendtion;

	private Object dataStructureElement;

	protected String name;
	
	private Long projectID;
	
	private Long id;

	private List<TestCaseUnit> testCaseUnitList = null;

	protected String path;

	public static final String PATH_SEPARATOR = "/";
	
	public static final String GLOABLE_PATH_PREFIX = PATH_SEPARATOR +  "Projects" + PATH_SEPARATOR;

	public static final String APPLICATION = "Application";

	public boolean isGloableDependency(){

		if(this.getExtendtion() != null && this.getExtendtion().getRestriction() != null){

			for(Item item : this.getExtendtion().getRestriction().getItem()){
				if(item.getMinExpression()!=null && item.getMinExpression().contains(GLOABLE_PATH_PREFIX)){
					return true;
				}
				if(item.getMaxExpression()!=null && item.getMaxExpression().contains(GLOABLE_PATH_PREFIX)){
					return true;
				}
			}
		}
		return false;
	}
//
//	private List<Integer[]> positionRecord = null;
//
//	public List<Integer[]> getPositionRecord() {
//		if(this.positionRecord == null){
//			positionRecord = new ArrayList<Integer[]>();
//		}
//		return positionRecord;
//	}
//
//	public void setPositionRecord(List<Integer[]> positionRecord) {
//		this.positionRecord = positionRecord;
//	}
//
//	private List<String> relativeManyToOnePathSet = null;
//
//	public List<String> getRelativeManyToOnePathSet() {
//
//		if(this.relativeManyToOnePathSet == null){
//			Set<String> tmpList = new HashSet<String>();
//			if( this.getExtendtion() !=null && this.getExtendtion().getRestriction() != null ){
//				if(this.getExtendtion().getRestriction().getMinStr() != null){
//					tmpList.addAll(TestCaseUtils.getAllAbsTestData( this.getExtendtion().getRestriction().getMinStr() ));
//				}
//				for(Item item : this.getExtendtion().getRestriction().getItem()){
//					tmpList.addAll(TestCaseUtils.getAllAbsTestData(item.getMinExpression()));
//					tmpList.addAll(TestCaseUtils.getAllAbsTestData(item.getMaxExpression()));
//				}
//			}
//			this.relativeManyToOnePathSet = new ArrayList<String>();
//			this.relativeManyToOnePathSet.addAll(tmpList);
//		}
//
//		return relativeManyToOnePathSet;
//	}
//
//	public void setRelativeManyToOnePathSet(List<String> relativeManyToOnePathSet) {
//		this.relativeManyToOnePathSet = relativeManyToOnePathSet;
//	}


	private boolean generateTestCaseFinish = false;
	
	public boolean isGenerateTestCaseFinish() {
		return generateTestCaseFinish;
	}

	public void setGenerateTestCaseFinish(boolean generateTestCaseFinish) {
		this.generateTestCaseFinish = generateTestCaseFinish;
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


	public List<TestCaseUnit> getTestCaseUnitList() {
		if(testCaseUnitList == null){
			testCaseUnitList = new ArrayList<TestCaseUnit>();
		}
		return testCaseUnitList;
	}

	public void setTestCaseUnitList(List<TestCaseUnit> testCaseUnitList) {
		this.testCaseUnitList = testCaseUnitList;
	}


	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public boolean equals(Object obj) {
		return this.getPath().equals(  ((AbstractTestData)obj).getPath()  ) ;
	}

}
