package com.fico.testCaseGenerator.XMLTestCaseGenerator;

import com.fico.testCaseGenerator.SimpleField;
import com.fico.testCaseGenerator.TestData;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class JavaTestData extends TestData {

    public JavaTestData(Class dataStructureCls){
        this.setDataStructureElement(dataStructureCls);
    }

    public Class getImplementClass(){
        return (Class)this.getDataStructureElement();
    }

    static{

        ConvertUtils.register(new Converter() {

            public Object convert(Class type, Object value) {

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    return simpleDateFormat.parse(value.toString());
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return null;
            }
        }, Date.class);
    }

    public boolean isBasicType(Class fieldType){
        if(fieldType.isPrimitive() ||
                fieldType == Integer.class || fieldType == Double.class ||
                fieldType == Float.class || fieldType == String.class ||
                fieldType == java.util.Date.class || fieldType == Short.class ||
                fieldType == Long.class || fieldType == Byte.class ||
                fieldType == Boolean.class || fieldType == Byte.class ||
                fieldType == Character.class || fieldType == Character.class
                ){
            return true;
        }
        return false;
    }

    @Override
    public String getName(){
        if(this.getImplementClass() != null){
            this.setName(getImplementClass().getSimpleName());
        }
        return this.name;
    }

    @Override
    public List<TestData> getCustomFieldList() {
        if (this.customFieldList == null) {
            this.generateCustomAndSimpleFieldList();;
        }
        return this.customFieldList;
    }

    @Override
    public List<SimpleField> getSimpleFieldList() {
       if(this.simpleFieldList == null){
           this.generateCustomAndSimpleFieldList();;
       }
       return this.simpleFieldList;
    }


    private void generateCustomAndSimpleFieldList(){
        Class thisCls = this.getImplementClass();

        List<TestData> customFieldsList = new ArrayList<TestData>();

        List<SimpleField> simpleFieldList = new ArrayList<SimpleField>();

        Field[] fieldsList = thisCls.getDeclaredFields();

        for (Field field : fieldsList) {

            //Class fieldCls = field.getType();

            Class fieldCls = JavaTestDataUtils.getFieldGenericType(field);

            if (!isBasicType(fieldCls)) {
                JavaTestData javaTestData = new JavaTestData( fieldCls );

                javaTestData.setDataStructureElement(fieldCls);

                javaTestData.setParentTestData(this);

                javaTestData.setParentFieldName( field.getName() );

                javaTestData.setName( field.getName() );

                customFieldsList.add(javaTestData);
            }else {
                JavaSimpleField javaSimpleField = new JavaSimpleField();

                javaSimpleField.setName( field.getName() );

                javaSimpleField.setTestData(this);

                javaSimpleField.setSimpleImplementField(field);

                simpleFieldList.add(javaSimpleField);
            }
        }
        this.customFieldList = customFieldsList;

        this.simpleFieldList = simpleFieldList;
    }



    @Override
    public String getPath() {
        if(this.path == null){

            if(this.getParentTestData() == null){
                this.path = this.getName() + '/';
                return this.path;
            }

            String parentFieldName = this.getParentFieldName();

            Class parentClass = (Class) this.getParentTestData().getDataStructureElement();

            Field parentfield = null;

            try {
                parentfield = parentClass.getDeclaredField(parentFieldName);

                Class genericType = JavaTestDataUtils.getFieldGenericType(parentfield);

                this.path = this.getParentTestData().getPath() +  genericType.getSimpleName() + "/";

            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }

    }
        return this.path;
    }
}
