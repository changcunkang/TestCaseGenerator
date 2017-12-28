package com.fico.testCaseGenerator.testCase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.fico.testCaseGenerator.data.SimpleField;
import com.fico.testCaseGenerator.data.TestData;
import com.fico.testCaseGenerator.BOM.BOMGenerator;
import com.fico.testCaseGenerator.data.xml.XMLTestData;
import com.fico.testCaseGenerator.data.configuration.Restriction;
import com.fico.testCaseGenerator.util.RandomFactory;

public class XMLTestCaseGenerator extends TestCaseGenerator {

	public XMLTestCaseGenerator(BOMGenerator bomGenerator) {
		super(bomGenerator);
	}

	protected void appendChildTestCaseToParentTestCase(Object parentIns, Object childIns, TestData childTestData) {

	}

	@Override
	public Object generateRootTestCaseData(TestData testData) {
		XMLTestData xmlTestData = (XMLTestData)testData;
		
		Element implEle = xmlTestData.getImplementXMLElement();
		String elementName = implEle.attributeValue("name");
		
		Element rootElement = DocumentHelper.createElement(elementName);  
        Document document = DocumentHelper.createDocument(rootElement);  
        
        generateTestCaseUnitDataAttribute( rootElement, xmlTestData );
        
		return rootElement;
	}

	


	private List<Element> generateAttr(Element parentTestCaseElement, int instanceSize, TestData testData){
		
		List<Element> newInstantceArr = new ArrayList<Element>();

		XMLTestData xmlTestData = (XMLTestData)testData;

		Element implEle = xmlTestData.getImplementXMLElement();
		String elementName = implEle.attributeValue("name");
		
		for(int i=0; i<instanceSize; i++){

			Element newInstanctElement = parentTestCaseElement.addElement(elementName);
			
			newInstantceArr.add( newInstanctElement );
			
			generateTestCaseUnitDataAttribute( newInstanctElement, xmlTestData );
		}
		
		return newInstantceArr;
	}
	
	private void generateTestCaseUnitDataAttribute(Element newInstantceArrElement, XMLTestData xmlTestData){
		if(xmlTestData.getSimpleFieldList() != null){
			for( SimpleField simpleField : xmlTestData.getSimpleFieldList() ){
				//generateSingleTestCaseAttributeValue(newInstantceArrElement, simpleField);
			}
		}
	}
	

	protected void setTestCaseInstanceSimpleFieldValue(Object newTestCaseInstance, String attributeName, Object attributeValue) {

	}

	
	private void createSimpleField( Element newInstantceArrElement, SimpleField simpleField ){
//
//		Restriction restriction = simpleField.getExtendtion().getRestriction();
//		String restrictionType = restriction.getType();
//
//		if( simpleField.getPath().indexOf("ScoreLstOfEmyerInCorpBlkLst") != -1 ){
//			System.out.println( simpleField.getPath() + " restriction type is null" );
//		}
//
//		double nullPercent = restriction.getNullPercentage();
//		double nullPercentHit = RandomFactory.random();
//		if( nullPercentHit > nullPercent ){
//
//			double notNullPercentHit = RandomFactory.random();
//
//			double minPercent = 0;
//
//			double maxPercent = 0;
//
//			for(int i=0; i<restriction.getItem().size(); i++){
//
//				minPercent = maxPercent;
//
//				maxPercent += restriction.getItem().get(i).getPercentage();
//
//				String attributeName = ((Element)simpleField.getSimpleImplementField()).attributeValue("name");
//
//				if(notNullPercentHit> minPercent && notNullPercentHit<= maxPercent){
//					//generateValue
//					if( Restriction.TYPE_ENMURATION.equals(restrictionType) ){
//
//						setTestCaseElementValue(simpleField,newInstantceArrElement, attributeName, restriction.getItem().get(i).getValue());
//					}
//					else{
//
//						String minRandomRangeStr = null;
//
//						if( i==0 ){
//							minRandomRangeStr = restriction.getMinStr();
//						}else{
//
//							minRandomRangeStr = restriction.getItem().get(i -1).getValue();
//						}
//
//						String maxRandomRangeStr = restriction.getItem().get(i).getValue();
//
//						String eleValue = null;
//
//						if( Restriction.TYPE_SECTION.equals(restrictionType) ){
//
//							try {
//								eleValue = generateSimpleAttrRandomValue( simpleField, minRandomRangeStr, maxRandomRangeStr );
//							} catch (Exception e) {
//								// TODO Auto-generated catch block
//								System.out.println( "Exception occur : " + simpleField.getPath() );
//
//								e.printStackTrace();
//							}
//
//							setTestCaseElementValue(simpleField, newInstantceArrElement, attributeName, eleValue);
//
//						}
//
//						else if( Restriction.TYPE_FUNCTION.equals(restrictionType) ){
//							try {
//								String functionRtn = customFunctionFactory.invokeCustomFunction(maxRandomRangeStr, null, simpleField);
//
//								setTestCaseElementValue(simpleField, newInstantceArrElement, attributeName, functionRtn);
//
//							} catch (Exception e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//						}
//					}
//				}
//			}
//		}
//		else{
//			addSimpleListTestCaseValue(simpleField, null );
//		}
	}
	
	private void setTestCaseElementValue(SimpleField simpleField , Element newInstantceArrElement, String attributeName, String eleValue){
		
		newInstantceArrElement.setAttributeValue(attributeName, eleValue);
		
		addSimpleListTestCaseValue(simpleField, eleValue);
	}
	
	private void addSimpleListTestCaseValue(SimpleField simpleField, String eleValue){
		
		simpleField.getTestCase().add(eleValue);

//		if(  this.bomGenerator.getSimpleFieldDependencyMap().get(simpleField.getPath()) != null ){
//			this.bomGenerator.getSimpleFieldDependencyMap().put( simpleField.getPath(), true );
//		}

		simpleField.setGenerateTestCaseFinish(true);
	}

	private String generateSimpleAttrRandomValue( SimpleField simpleField, String min, String max ) throws Exception {
		
		String rtn  = null;
		
		if(simpleField.getFieldType() == SimpleField.TYPE_INT){
			
			int eleValueInt = RandomFactory.randomIntBetween(new Double(min).intValue(), new Double(max).intValue() );
			
			rtn = String.valueOf(eleValueInt);
			
		}else if( simpleField.getFieldType() == SimpleField.TYPE_REAL ){
			
			double eleValueDouble = RandomFactory.randomDoubleBetween(Double.parseDouble( min ), Double.parseDouble( max ) );
			
			
			rtn = String.valueOf(new Double(eleValueDouble));
		
		}else if( simpleField.getFieldType() == SimpleField.TYPE_DATE ){
		
			Date dateMin = this.getDateTypeDataFormat().parse(min);	
			
			Date dateMax = this.getDateTypeDataFormat().parse(max);	
			
			Date rntDate = RandomFactory.randomDateBetween(dateMin, dateMax);
			
			rtn = this.getDateTypeDataFormat().format(rntDate);
			
		}else if( simpleField.getFieldType() == SimpleField.TYPE_DATETIME ){
		
			Date dateMin = this.getDateTimeTypeDataFormat().parse(min);	
			
			Date dateMax = this.getDateTimeTypeDataFormat().parse(max);	
			
			Date rntDate = RandomFactory.randomDateBetween(dateMin, dateMax);
			
			rtn = this.getDateTimeTypeDataFormat().format(rntDate);
		}
		
		return rtn;
	}

	protected Object createEmptyTestCaseInstance(TestData testData) {
		return null;
	}

	protected Object TestCaseInstanceCopy(Object testCaseInstance) {
		return null;
	}

}
