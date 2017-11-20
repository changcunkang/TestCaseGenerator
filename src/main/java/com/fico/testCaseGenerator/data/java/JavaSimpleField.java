package com.fico.testCaseGenerator.data.java;

import com.fico.testCaseGenerator.data.SimpleField;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JavaSimpleField extends SimpleField {

    private static Map<Class, Integer> xsdFieldTpyeMap = null;

    static{
        xsdFieldTpyeMap = new ConcurrentHashMap<Class, Integer>();

        xsdFieldTpyeMap.put(int.class, SimpleField.TYPE_INT);
        xsdFieldTpyeMap.put(Integer.class, SimpleField.TYPE_INT);
        xsdFieldTpyeMap.put(long.class, SimpleField.TYPE_INT);
        xsdFieldTpyeMap.put(Long.class, SimpleField.TYPE_INT);
        xsdFieldTpyeMap.put(short.class, SimpleField.TYPE_INT);
        xsdFieldTpyeMap.put(Short.class, SimpleField.TYPE_INT);

        xsdFieldTpyeMap.put(String.class, SimpleField.TYPE_STRING);

        xsdFieldTpyeMap.put(double.class, SimpleField.TYPE_REAL);
        xsdFieldTpyeMap.put(Double.class, SimpleField.TYPE_REAL);
        xsdFieldTpyeMap.put(float.class, SimpleField.TYPE_REAL);
        xsdFieldTpyeMap.put(Float.class, SimpleField.TYPE_REAL);

        xsdFieldTpyeMap.put(java.util.Date.class, SimpleField.TYPE_DATE);

        xsdFieldTpyeMap.put(boolean.class, SimpleField.TYPE_BOOLEAN);
        xsdFieldTpyeMap.put(Boolean.class, SimpleField.TYPE_BOOLEAN);


        //xsdFieldTpyeMap.put("xs:dateTime", SimpleField.TYPE_DATETIME);
    }


    public int getFieldType() {

        if(this.getSimpleImplementField() != null){
            Field field = (Field)this.getSimpleImplementField();

            Class fieldType = field.getType();

            if(fieldType != null){

                Integer rntInt = xsdFieldTpyeMap.get(fieldType);

                return rntInt;
            }
        }

        return super.getFieldType();
    }

    @Override
    public String getPath() {
        if(this.path == null){

            Field field = (Field)this.getSimpleImplementField();

//            Class genericClass = JavaTestDataUtils.getFieldGenericType(field);
//
//            String tmpPath = genericClass.getSimpleName();

            this.path = this.getTestData().getPath() + "@" + field.getName() + "/";
        }
        return this.path;
    }
}
