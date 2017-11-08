package com.fico.testCaseGenerator.data.xml;

import org.dom4j.Element;

import com.fico.testCaseGenerator.data.TestData;


public class XMLTestData extends TestData {
	
	public Element getImplementXMLElement(){
		return (Element)super.getDataStructureElement();
	}

	public void generateSimpleFileds() {
		
	}

	public void generateCustomFields() {
		// TODO Auto-generated method stub
	}


}
