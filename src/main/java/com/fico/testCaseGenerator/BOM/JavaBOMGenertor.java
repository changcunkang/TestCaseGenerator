package com.fico.testCaseGenerator.BOM;

import com.fico.testCaseGenerator.data.TestData;
import com.fico.testCaseGenerator.data.java.JavaTestData;
import com.fico.testCaseGenerator.factory.LoadConfiguration;

import java.util.ResourceBundle;

public class JavaBOMGenertor extends BOMGenerator {

    public JavaBOMGenertor (LoadConfiguration loadconf){
        this.loadconf = loadconf;

        this.buildTestDataStructor();
    }

    public TestData getRootTestData() {

        if(this.rootTestData != null){
            return this.rootTestData;
        }

        String applicationClassFullPackageName = ResourceBundle.getBundle("javaBOM").getString("rootApplicationFullName");

        Class applicationClass = null;

        try {
            applicationClass = Class.forName( applicationClassFullPackageName );
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if(applicationClass != null){
            this.rootTestData = new JavaTestData(applicationClass);
        }

        return this.rootTestData;
    }

}
