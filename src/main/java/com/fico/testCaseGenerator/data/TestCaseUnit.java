package com.fico.testCaseGenerator.data;

import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author XiangbinYuan stevenYuan
 * @CreationDate 12/27/2017
 */
public class TestCaseUnit {

    private TestData testData;

    private Object testCaseInstance;

    private boolean isFirstChild = false;

    private boolean isLastChild = false;

    private TestCaseUnit parentTestCaseUnit;

    private int positionInParent;

    private TestCaseUnit[] brotherListForOneParent;

    public Object getTestCaseInstance() {
        return testCaseInstance;
    }

    public void setTestCaseInstance(Object testCaseInstance) {
        this.testCaseInstance = testCaseInstance;
    }

    public boolean isFirstChild() {
        return isFirstChild;
    }

    public void setFirstChild(boolean firstChild) {
        isFirstChild = firstChild;
    }

    public boolean isLastChild() {
        return isLastChild;
    }

    public void setLastChild(boolean lastChild) {
        isLastChild = lastChild;
    }

    public TestCaseUnit getParentTestCaseUnit() {
        return parentTestCaseUnit;
    }

    public void setParentTestCaseUnit(TestCaseUnit parentTestCaseUnit) {
        this.parentTestCaseUnit = parentTestCaseUnit;
    }

    public int getPositionInParent() {
        return positionInParent;
    }

    public void setPositionInParent(int positionInParent) {
        this.positionInParent = positionInParent;
    }

    public TestCaseUnit[] getBrotherListForOneParent() {
        return brotherListForOneParent;
    }

    public void setBrotherListForOneParent(TestCaseUnit[] brotherListForOneParent) {
        this.brotherListForOneParent = brotherListForOneParent;
    }

    public TestData getTestData() {
        return testData;
    }

    public void setTestData(TestData abstractTestData) {
        this.testData = abstractTestData;
    }

    public Object getFieldValue(String fieldName){
        try {
            String attrName = fieldName.substring(0,1).toLowerCase() +  fieldName.substring(1);

           return PropertyUtils.getProperty(this.getTestCaseInstance(), attrName);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
