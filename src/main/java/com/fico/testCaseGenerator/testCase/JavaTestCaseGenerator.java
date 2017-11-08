package com.fico.testCaseGenerator.testCase;

import com.fico.testCaseGenerator.BOM.BOMGenerator;
import com.fico.testCaseGenerator.data.TestData;
import com.fico.testCaseGenerator.data.java.JavaTestData;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JavaTestCaseGenerator extends TestCaseGenerator {

    public JavaTestCaseGenerator(BOMGenerator bomGenerator) {
        super(bomGenerator);
    }

    protected Object createEmptyTestCaseInstance(TestData testData){

        JavaTestData javaTestData = (JavaTestData) testData;

        Class testDataCls = javaTestData.getImplementClass();

        Object newTestCaseIns = null;

        try {
            newTestCaseIns = testDataCls.newInstance();

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return newTestCaseIns;
    }

    protected Object TestCaseInstanceCopy(Object testCaseInstance) {
        try {
            return BeanUtils.cloneBean(testCaseInstance);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object generateRootTestCaseData(TestData testData) {

        Object rootTestCaseInstance = createEmptyTestCaseInstance(testData);

        generateSingleTestCaseInstance(rootTestCaseInstance, testData);

        return rootTestCaseInstance;

    }

    protected void appendChildTestCaseToParentTestCase(Object parentIns, Object childIns, TestData childTestData) {

        TestData parentTestData = childTestData.getParentTestData();

        if(parentTestData != null){
            Class parentCls = (Class)parentTestData.getDataStructureElement();

            try {
                Field childField = parentCls.getDeclaredField( childTestData.getParentFieldName() );

                if(Collection.class.isAssignableFrom(childField.getType())){
                    List parentChildList = (List) PropertyUtils.getProperty( parentIns, childTestData.getParentFieldName() );

                    if(parentChildList == null){
                        parentChildList = new ArrayList();
                    }

                    parentChildList.add(childIns);

                }else{
                    BeanUtils.setProperty(parentIns, childTestData.getParentFieldName(), childIns);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    protected void setTestCaseInstanceSimpleFieldValue(Object newTestCaseInstance, String attributeName, Object attributeValue) {
        try {
            BeanUtils.setProperty(newTestCaseInstance, attributeName, attributeValue);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
