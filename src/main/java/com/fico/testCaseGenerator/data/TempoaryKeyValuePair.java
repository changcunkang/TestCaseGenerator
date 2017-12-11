package com.fico.testCaseGenerator.data;

/**
 * @Author XiangbinYuan stevenYuan
 * @CreationDate 12/1/2017
 */
public class TempoaryKeyValuePair {

    public TempoaryKeyValuePair(String attributeName, Object attributeValue){
        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
    }

    private String attributeName;

    private Object attributeValue;

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public Object getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(Object attributeValue) {
        this.attributeValue = attributeValue;
    }
}
