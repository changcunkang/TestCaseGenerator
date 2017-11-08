package com.fico.testCaseGenerator.BOM;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.fico.testCaseGenerator.data.SimpleField;
import com.fico.testCaseGenerator.data.TestData;
import com.fico.testCaseGenerator.data.xml.XMLSimpleField;
import com.fico.testCaseGenerator.data.xml.XMLTestData;
import com.fico.testCaseGenerator.configuration.Extendtion;
import com.fico.testCaseGenerator.factory.LoadConfiguration;

public class XMLBOMGenerator extends BOMGenerator {

	public static final String NS_PREFIX = "XS";

	public static final String NS_URI = "http://www.w3.org/2001/XMLSchema";

	public static final String NS_CONNECTOR = ":";

	public synchronized static String getElementXPATH(String elementName) {
		return NS_PREFIX + NS_CONNECTOR + "element[@name='" + elementName +"']";
	}

	public synchronized static String getSimpleFieldXPATH() {
		return NS_PREFIX + NS_CONNECTOR + "complexType/" + NS_PREFIX
				+ NS_CONNECTOR + "attribute";
	}

	public synchronized static String getCustomFieldXPATH() {
		return NS_PREFIX + NS_CONNECTOR + "complexType/" + NS_PREFIX + NS_CONNECTOR + "sequence/" + NS_PREFIX + NS_CONNECTOR + "element";
	}

	private String XSDFilePath;

	private Element rootElement;
	
	public XMLBOMGenerator(LoadConfiguration loadconf){
		this.loadconf = loadconf;
	}
	
	public XMLBOMGenerator(){
		super();
	}
	
	
	public String getXSDFilePath() {
		return "C:\\projects\\testCaseGenerator\\eclipse_work_space\\TestCaseGeneratorWeb\\BOM\\"  + this.loadconf.getProjectName() +  "\\Application_" + this.loadconf.getProjectName() + ".xsd";
	}

	@Override
	public TestData getRootTestData() {

		// TODO Auto-generated method stub
		// 未完成：通过projectName去找XSD的路径
		String xsdFilePath = getXSDFilePath();

		SAXReader reader = new SAXReader();

		Map<String, String> nameSpaceMap = new HashMap<String, String>();
		nameSpaceMap.put(NS_PREFIX, NS_URI);

		reader.getDocumentFactory().setXPathNamespaceURIs(nameSpaceMap);

		Document document = null;

		try {
			document = reader.read(new File(xsdFilePath));

			Element rootEl = document.getRootElement();
			
			this.rootElement = rootEl;
			
			Element applicationEle = (Element) rootEl.selectSingleNode(getElementXPATH("Application"));

			rootTestData = new XMLTestData();
			
			rootTestData.setName("Application");
			
			rootTestData.setDataStructureElement(applicationEle);
			
			generateSimpleAttribute(getXMLRootTestData());
			
			this.addTestDataToMap(rootTestData);
			
			recusiveBuildTestData(getXMLRootTestData());

		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rootTestData;

	}


	private XMLTestData getXMLRootTestData(){
		return (XMLTestData)this.rootTestData;
	}
	
	//parentTestData 已经构造好
	public TestData recusiveBuildTestData(TestData testData){

		XMLTestData parentTestData = (XMLTestData)testData;

		Element parentTestDataEL = parentTestData.getImplementXMLElement();
		
		List<Element> customElList = getCustomElementList(parentTestDataEL);
		
		if(customElList != null && customElList.size() > 0){
			for( Element customEl : customElList ){
				XMLTestData childTestData = new XMLTestData();
				
				childTestData.setDataStructureElement(customEl);
				
				childTestData.setParentTestData(parentTestData);
				
				childTestData.setName(customEl.attributeValue("name"));
				
				generateSimpleAttribute(childTestData);

				//this.addTestDataToMap(childTestData);
				
				recusiveBuildTestData(childTestData);
				
				parentTestData.getCustomFieldList().add(childTestData);
			}
		}

		return testData;
	}

	private void generateSimpleAttribute(XMLTestData targetTestData){
		generatePath( targetTestData );
		buildSimpleField( targetTestData );
		
		Extendtion extendtion = this.configurationMap.get(targetTestData.getPath());
		
		if(extendtion != null){
			targetTestData.setExtendtion(extendtion);
			
			this.addTestDataToMap(targetTestData);
			
			if( extendtion.getRestriction() != null && extendtion.getRestriction().getTransfersType() == 2 ){
				System.out.println("find output extendtion for element : " + targetTestData.getPath());
			}
		}
	}
	
	private List<Element> getCustomElementList(Element parentTestDataEL){
		List<Element> refElList = parentTestDataEL.selectNodes( getCustomFieldXPATH() );
		List<Element> childElementList = null;
		if(refElList != null && refElList.size() >0){
			childElementList = new ArrayList<Element>();
			for( Element refEl : refElList ){
				String refElementName = refEl.attributeValue("ref");
				
				if(refElementName != null){
					Element refTargetElement = (Element) this.rootElement.selectSingleNode( getElementXPATH( refElementName ) );
					
					if( refTargetElement == null ){
						System.out.println(refElementName + "is null");
					}
					
					childElementList.add(refTargetElement);
				}
			}
		}
		return childElementList;
	}
	
	private void generatePath(XMLTestData targetTestData){
		
		Element targetTestDataEL = targetTestData.getImplementXMLElement();
		
		String eleName = targetTestDataEL.attributeValue("name") + "/";
		
		if(targetTestData.getParentTestData() != null){
			eleName = targetTestData.getParentTestData().getPath()  + eleName;
		}
		
		targetTestData.setPath(eleName);
	}
	
	private void buildSimpleField(XMLTestData targetTestData){
		
		Element targetTestDataEL = targetTestData.getImplementXMLElement();
		
		List<Element> simpleXMLAttrListEl = targetTestDataEL.selectNodes( getSimpleFieldXPATH() );
		
		if(simpleXMLAttrListEl != null && simpleXMLAttrListEl.size() >0){
			
			for(Element simpleXMLAttrEl : simpleXMLAttrListEl){
			
				XMLSimpleField tmpXMLSimpleField = new XMLSimpleField();
				
				String testDataPath = targetTestData.getPath();
				
				tmpXMLSimpleField.setSimpleImplementField(simpleXMLAttrEl);
				
				tmpXMLSimpleField.setPath( testDataPath + "@" +  simpleXMLAttrEl.attributeValue("name"));
				
				tmpXMLSimpleField.setName(simpleXMLAttrEl.attributeValue("name"));
				
				Extendtion extendtion = this.configurationMap.get(tmpXMLSimpleField.getPath());
				
				if(extendtion != null){
					tmpXMLSimpleField.setExtendtion(extendtion);
					
					addTestDataToMap( tmpXMLSimpleField );
				}
				
				targetTestData.getSimpleFieldList().add(tmpXMLSimpleField);
				
				tmpXMLSimpleField.setTestData(targetTestData);
			}
		}

//		for(SimpleField sf : targetTestData.getSimpleFieldList()){
//			this.getPathSimpleFieldMap().put( sf.getPath(), sf );
//		}
	}
	
	private void addTestDataToMap(Object testDataOrSimpleAttr){
		
		if(testDataOrSimpleAttr instanceof TestData){
			
			TestData testData = (TestData)testDataOrSimpleAttr;
			
			this.getPathTestDataMap().put(testData.getPath(), testData);		
			
			if( testData.getExtendtion() != null && testData.getExtendtion().getDependency() != null && testData.getExtendtion().getDependency().getParentPath() != null ){
				this.getTestDataDependencyMap().put(testData.getExtendtion().getDependency().getParentPath(), false);
				
				this.getDependencyTestDataList().add(testData);
			}

		}else if(testDataOrSimpleAttr instanceof SimpleField){
			
			SimpleField simpleField = (SimpleField)testDataOrSimpleAttr;
			
			this.getPathSimpleFieldMap().put(simpleField.getPath(), simpleField);
			
			if(simpleField.getExtendtion().getRestriction() != null && simpleField.getExtendtion().getRestriction().getTransfersType() == 2){
				this.getOutputSimpleFieldList().add(simpleField);
			}
			
			if( simpleField.getExtendtion() != null && simpleField.getExtendtion().getDependency() != null && simpleField.getExtendtion().getDependency().getParentPath() != null ){
				this.getSimpleFieldDependencyMap().put(simpleField.getExtendtion().getDependency().getParentPath(), false);
				
				this.getDependencySimpleFieldList().add(simpleField);
			}
		}
	}
}
