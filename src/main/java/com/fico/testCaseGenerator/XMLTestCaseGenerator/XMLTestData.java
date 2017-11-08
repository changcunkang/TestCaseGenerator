package com.fico.testCaseGenerator.XMLTestCaseGenerator;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.fico.testCaseGenerator.SimpleField;
import com.fico.testCaseGenerator.TestData;
import com.fico.testCaseGenerator.BOM.XMLBOMGenerator;
import com.fico.testCaseGenerator.configuration.Dependency;
import com.fico.testCaseGenerator.configuration.Item;
import com.fico.testCaseGenerator.configuration.Restriction;
import com.fico.testCaseGenerator.util.RandomFactory;


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
