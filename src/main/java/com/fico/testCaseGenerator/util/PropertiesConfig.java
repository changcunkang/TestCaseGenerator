package com.fico.testCaseGenerator.util;

import java.util.ResourceBundle;

/**
 * @Author XiangbinYuan stevenYuan
 * @CreationDate 12/29/2017
 */
public class PropertiesConfig {

    private static ResourceBundle rb = ResourceBundle.getBundle("testCaseGenerator");

    public static String getRootApplicationClassStr(){
        return rb.getString("rootApplicationPackageName");
    }

    public static String getOutputElementClass(){
        return rb.getString("outputElementClass");
    }

    public static String getRootApplicationPackageFirstName(){
        return rb.getString("rootApplicationPackageFirstName");
    }

    public static String getString(String value){
        return rb.getString(value);
    }

}
