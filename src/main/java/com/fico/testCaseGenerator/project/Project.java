package com.fico.testCaseGenerator.project;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fico.testCaseGenerator.BOM.JavaBOMGenertor;
import com.fico.testCaseGenerator.testCase.JavaTestCaseGenerator;
import org.dom4j.Attribute;
import org.dom4j.Element;

import com.blazesoft.server.base.NdServiceException;
import com.blazesoft.server.base.NdServiceSessionException;
import com.blazesoft.server.deploy.NdStatelessServer;
import com.blazesoft.server.local.NdLocalServerException;
import com.fico.testCaseGenerator.data.SimpleField;
import com.fico.testCaseGenerator.data.TestData;
import com.fico.testCaseGenerator.BOM.BOMGenerator;
import com.fico.testCaseGenerator.configuration.Item;
import com.fico.testCaseGenerator.configuration.Restriction;
import com.fico.testCaseGenerator.factory.LoadConfiguration;
import com.fico.testCaseGenerator.testCase.TestCaseGenerator;

public class Project {

	public Project(){
	}
	
	public static final int PROJECT_TYPE_XMLBOM = 0;
	
	public static final int PROJECT_TYPE_JAVABOM = 1;
	
	private int projectType;
	
	private BOMGenerator bomGernerator;
	
	private TestCaseGenerator testCaseGenerator;
	
	private LoadConfiguration loadconf;
	
	private Long projectID;
	
	private String serverFilePath;
	
	private String projectName;

	private TestData rootTestData;
	
	private static Map<String, String> ruleServiceNameMap = new HashMap<String, String>();
	
	private static Map<String, NdStatelessServer> blazeServerMap = new HashMap<String, NdStatelessServer>();
	
	private static Map<String, List> projectOutputSimpleFieldListMap = new HashMap<String, List>();
	
	static {
		ruleServiceNameMap.put("FRD", "FraudRuleService");
		ruleServiceNameMap.put("RSK", "RiskRuleService");
		ruleServiceNameMap.put("OPR", "OPRRuleService");
	}
	
	public NdStatelessServer getBlazeServer() {
		
		if(blazeServerMap.get(this.getProjectName()) == null){
			try {
				NdStatelessServer blazeServer = NdStatelessServer.createStatelessServer(this.getServerFilePath());
			
				blazeServerMap.put(this.getProjectName(), blazeServer);
			} catch (NdLocalServerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return blazeServerMap.get(this.getProjectName());
	}

	private String getServerFilePath(){
		if(this.serverFilePath == null){
			return "C:\\projects\\testCaseGenerator\\eclipse_work_space\\TestCaseGenerator\\adb\\"  + this.getProjectName() + "\\" + this.getProjectName() + "_ser.server";
		}
		return this.serverFilePath;
	}
	
	public void setServerFilePath(String serverFilePath) {
		this.serverFilePath = serverFilePath;
	}
	
	public String getGloableProjectPath(){
		return TestData.GLOABLE_PATH_PREFIX + this.getProjectName() + TestData.PATH_SEPARATOR;
	}
	
	public TestData getRootTestData() {
		
		if( rootTestData == null ){
			rootTestData = this.getBomGernerator().buildTestDataStructor();
		}
		
		return rootTestData;
	}

	public void setRootTestData(TestData rootTestData) {
		this.rootTestData = rootTestData;
	}

	public Project(String projectName, int projectType){
		this.projectName = projectName;
		
		this.projectType = projectType;
		
		this.loadconf = new LoadConfiguration(projectName);
	}
	
	public int getProjectType() {
		return projectType;
	}

	public void setProjectType(int projectType) {
		this.projectType = projectType;
	}

	public BOMGenerator getBomGernerator() {
		
		if(this.bomGernerator == null){
			
			if(this.projectType == Project.PROJECT_TYPE_XMLBOM){
				this.bomGernerator = new JavaBOMGenertor( this.getLoadconf() );
			}
		}
		
		return bomGernerator;
	}

	public void setBomGernerator(BOMGenerator bomGernerator) {
		this.bomGernerator = bomGernerator;
	}

	public TestCaseGenerator getTestCaseGenerator() {
		
		if(this.testCaseGenerator == null){
			this.testCaseGenerator = new JavaTestCaseGenerator( getBomGernerator() );
		}
		
		return testCaseGenerator;
	}

	public void setTestCaseGenerator(TestCaseGenerator testCaseGenerator) {
		this.testCaseGenerator = testCaseGenerator;
	}

	public LoadConfiguration getLoadconf() {
		
		if( this.loadconf == null ){
			this.loadconf = new LoadConfiguration(projectName); 
		}
		
		return loadconf;
	}

	public void setLoadconf(LoadConfiguration loadconf) {
		this.loadconf = loadconf;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	public Object generateTestCase(){
		
		return this.getTestCaseGenerator().generateTestCase() ;
	}

	public String invokeBlazeService(String blazeInput){
		
		Object[] applicationArgs = new Object[1];
		applicationArgs[0] = blazeInput;
		
		String blazeOutput = null;
		
		try {
			blazeOutput = (String)this.getBlazeServer().invokeService(this.ruleServiceNameMap.get(this.getProjectName()), "invokeExternalMain", null, applicationArgs);
		} catch (NdServiceSessionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NdLocalServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NdServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return blazeOutput;
	}
	
	public synchronized boolean addToResultPool(Element blazeOutput){
		List ll = this.getOutputSimpleFieldList();
		
		if(this.getOutputSimpleFieldList() == null || this.getOutputSimpleFieldList().size() ==0){
			return true;
		}
		
		boolean rtn = false;
		if(this.getOutputSimpleFieldList() != null){
			
			for(SimpleField sf : this.getOutputSimpleFieldList()){
				Object outputValue = getOutputSimpleFieldValue(blazeOutput, sf.getPath());
				
				if("A".equals(outputValue)){
					String a = "";
				}
				
				if( sf.getExtendtion().getRestriction().getType() == Restriction.TYPE_ENMURATION){
					
					for(Item item : sf.getExtendtion().getRestriction().getItem()){
						
						if(outputValue!= null && outputValue.equals(item.getValue()) && item.addResultID(getApplicationID(blazeOutput))){
							
							rtn = true;
							return rtn;
						}
					}
				}
			}
		}
		return rtn;
	}
	
	public Object getOutputSimpleFieldValue(Element blazeOutput, String simpleFieldPath){
		Attribute attri = (Attribute)blazeOutput.selectSingleNode("/" + simpleFieldPath);
		
		if(attri != null){
			return attri.getValue();
		}
		return null;
	}
	
	public String getApplicationID( Element blazeOutput ){
		Attribute attr = (Attribute)blazeOutput.selectSingleNode("/Application/ApplicationForm[1]/@ApplicationID");
		return attr.getValue();
	}
	
	public Long getProjectID() {
		return projectID;
	}

	public void setProjectID(Long projectID) {
		this.projectID = projectID;
	}

	public  List<SimpleField> getOutputSimpleFieldList() {
		
		if(projectOutputSimpleFieldListMap.get(this.getProjectName()) == null){
			
			this.getBomGernerator().getRootTestData();
			
			projectOutputSimpleFieldListMap.put(this.getProjectName(), this.getBomGernerator().getOutputSimpleFieldList());
		}
		
		return projectOutputSimpleFieldListMap.get(this.getProjectName());
	}
}
