package com.fico.testCaseGenerator.XMLTestCaseGenerator;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.Element;

import com.fico.testCaseGenerator.SimpleField;

public class XMLSimpleField extends SimpleField {

	private static Map<String, Integer> xsdFieldTpyeMap = null;
	
	static{
		xsdFieldTpyeMap = new HashMap<String, Integer>();
		
		xsdFieldTpyeMap.put("xs:integer", SimpleField.TYPE_INT);
		
		xsdFieldTpyeMap.put("xs:string", SimpleField.TYPE_STRING);
		
		xsdFieldTpyeMap.put("xs:double", SimpleField.TYPE_REAL);
		
		xsdFieldTpyeMap.put("xs:date", SimpleField.TYPE_DATE);
		
		xsdFieldTpyeMap.put("xs:boolean", SimpleField.TYPE_BOOLEAN);
		
		xsdFieldTpyeMap.put("xs:dateTime", SimpleField.TYPE_DATETIME);
	}
	
	public int getFieldType() {
		
		if(this.getSimpleImplementField() != null){
			Element implEle = (Element)this.getSimpleImplementField();
			
			String xsdAttrType = implEle.attributeValue("type");
			
			if(xsdAttrType != null){
				
				Integer rntInt = xsdFieldTpyeMap.get(xsdAttrType);
				
				if( rntInt == null ){
					System.out.println( xsdAttrType + " type is null " );
				}
				
				return rntInt;
			}
		}
		
		return super.getFieldType();
	}
	
}
