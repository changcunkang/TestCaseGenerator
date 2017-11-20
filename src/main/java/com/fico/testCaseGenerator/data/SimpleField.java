package com.fico.testCaseGenerator.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class SimpleField extends AbstractTestData {
	
	public static final int TYPE_INT = 1;
	
	public static final int TYPE_REAL = 2;
	
	public static final int TYPE_BOOLEAN = 3;
	
	public static final int TYPE_DATE = 4;
	
	public static final int TYPE_STRING = 5;
	
	public static final int TYPE_DATETIME = 6;
	
	private static Map<Integer, String> fieldTypeStrMap = new HashMap<Integer, String>();
	
	static{
		fieldTypeStrMap.put(1, "integer");
		fieldTypeStrMap.put(2, "real");
		fieldTypeStrMap.put(3, "boolean");
		fieldTypeStrMap.put(4, "date");
		fieldTypeStrMap.put(5, "string");
		fieldTypeStrMap.put(6, "datetime");
	}
	
	private int fieldType;
	
	private Object simpleImplementField;
	
	protected String path;
	
	private TestData testData;
	
	private long testDataId;	

	public TestData getTestData() {
		return testData;
	}

	public void setTestData(TestData testData) {
		this.testData = testData;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getFieldType() {
		return fieldType;
	}

	public String getFieldTypeStr() {
		return this.fieldTypeStrMap.get( getFieldType() );
	}
	
	public void setFieldType(int fieldType) {
		this.fieldType = fieldType;
	}

	public Object getSimpleImplementField() {
		return simpleImplementField;
	}

	public void setSimpleImplementField(Object simpleImplementField) {
		this.simpleImplementField = simpleImplementField;
	}

	public long getTestDataId() {
		return testDataId;
	}

	public void setTestDataId(long testDataId) {
		this.testDataId = testDataId;
	}


	
}
