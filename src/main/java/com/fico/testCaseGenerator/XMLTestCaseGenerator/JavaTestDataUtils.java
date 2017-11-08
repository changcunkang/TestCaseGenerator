package com.fico.testCaseGenerator.XMLTestCaseGenerator;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;

public class JavaTestDataUtils {

    public static Class getFieldGenericType(Field field){

        Class fieldType = field.getType();

        if(!Collection.class.isAssignableFrom( fieldType )){
            return fieldType;
        }
        else{
            //list 获取泛型
            ParameterizedType pt = (ParameterizedType) field.getGenericType();

            return (Class)pt.getActualTypeArguments()[0]; //【4】 得到泛型里的class类型对象。
        }
    }

}
