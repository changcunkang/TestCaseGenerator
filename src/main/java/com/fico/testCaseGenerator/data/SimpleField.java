package com.fico.testCaseGenerator.data;

import com.fico.testCaseGenerator.data.configuration.Item;
import com.fico.testCaseGenerator.util.TestCaseUtils;
import org.apache.commons.math3.stat.inference.TestUtils;

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

	private List<Integer> dependencySimpleFieldPosition;

	static{
		fieldTypeStrMap.put(1, "integer");
		fieldTypeStrMap.put(2, "real");
		fieldTypeStrMap.put(3, "boolean");
		fieldTypeStrMap.put(4, "date");
		fieldTypeStrMap.put(5, "string");
		fieldTypeStrMap.put(6, "datetime");
	}

	private List testCase;

	public List getTestCase() {
		if(testCase == null){
			testCase = new ArrayList();
		}
		return testCase;
	}

	public void setTestCase(List testCase) {
		this.testCase = testCase;
	}

	private int fieldType;

	private Object simpleImplementField;

	private TestData testData;

	public TestData getTestData() {
		return testData;
	}

	public void setTestData(TestData testData) {
		this.testData = testData;
	}

	public int getFieldType() {
		return fieldType;
	}

	public Object getSimpleImplementField() {
		return simpleImplementField;
	}

	public void setSimpleImplementField(Object simpleImplementField) {
		this.simpleImplementField = simpleImplementField;
	}

	@Override
	public String toString(){
		return this.getName();
	}

}
