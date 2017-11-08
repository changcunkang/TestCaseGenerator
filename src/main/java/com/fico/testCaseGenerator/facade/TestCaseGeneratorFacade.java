package com.fico.testCaseGenerator.facade;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import com.fico.testCaseGenerator.blazeServer.BlazeServers;
import com.fico.testCaseGenerator.configuration.Item;
import com.fico.testCaseGenerator.configuration.Restriction;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.InitializingBean;

import com.fico.testCaseGenerator.AbstractTestData;
import com.fico.testCaseGenerator.SimpleField;
import com.fico.testCaseGenerator.TestData;
import com.fico.testCaseGenerator.factory.LoadConfiguration;
import com.fico.testCaseGenerator.project.Project;
import com.fico.testCaseGenerator.testCase.RIDOInstance;
import com.fico.testCaseGenerator.testCase.TestCaseGenerator;
import com.fico.testCaseGenerator.testCase.TestCaseInstance;

public class TestCaseGeneratorFacade implements InitializingBean {

	private List<Project> projectList = new ArrayList<Project>();

	private Map<String, Map> projectLevelDependencyMap = new HashMap<String, Map>();

	private List<TestData> projectLevelDependencyTestDataList = new ArrayList<TestData>();

	private List<SimpleField> simpleFieldLevelDependencyTestDataList = new ArrayList<SimpleField>();

	private Map<AbstractTestData, TestCaseGenerator> absTestData_TestCaseGenerator = new HashMap<AbstractTestData, TestCaseGenerator>();

	private static List<SimpleField> projectLevelOutputSimpleFieldList = null;

	public List<String> listProjects() {
		return LoadConfiguration.listAllProjectsName();
	}

	public void loadAllProjects() {
		List<String> projectsNameList = this.listProjects();

		for (String tmpProjectName : projectsNameList) {
			loadProject(tmpProjectName);
		}
	}

	public void setOutputPool(Long numCases){

		for(SimpleField outputSimpleField : this.getProjectLevelOutputSimpleFieldList()){
			if(outputSimpleField.getExtendtion() != null && outputSimpleField.getExtendtion().getRestriction() != null){
				Restriction restriction = outputSimpleField.getExtendtion().getRestriction();
				if(restriction != null && restriction.getItem()!=null ){
					for(Item item : restriction.getItem()){

						long resultMaxNum = (long) (numCases * item.getPercentage());

						item.setResultMaxNum( resultMaxNum );
					}
				}
			}
		}
	}

	public List<Project> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<Project> projectList) {
		this.projectList = projectList;
	}

	public void loadProject(String projectName) {
		Project tmpProject = new Project(projectName,
				Project.PROJECT_TYPE_XMLBOM);

		projectList.add(tmpProject);
	}

	public Project getProject(String projectName) {
		for (Project project : projectList) {
			if (projectName != null
					&& projectName.equals(project.getProjectName())) {
				return project;
			}
		}
		return null;
	}

	public void manageProjectLevelMasterAndSlave() {

		buildProjectLevelTestCaseDependencyList();
		buildProjectLevelSimpleFieldDependencyList();

		boolean loopFlag = true;

		while (loopFlag) {
			int projectLevelDependencyTestDataListSize = this.projectLevelDependencyTestDataList
					.size();

			for (int i = 0; i < projectLevelDependencyTestDataList.size(); i++) {

				TestData testData = projectLevelDependencyTestDataList.get(i);

				for (Project project : this.projectList) {
					if (!project.getTestCaseGenerator().isMasterAvailable(
							testData)
							&& testData.isGloableDependency()) {
						String gloableParentPath = testData.getExtendtion()
								.getDependency().getParentPath();

						TestData targetProjectLoevelDependencyTestData = getProjectLevelTestData(gloableParentPath);

						if (targetProjectLoevelDependencyTestData != null) {

							for (Object paretnTestCase : testData
									.getParentTestData().getTestCase()) {

								TestCaseGenerator tcG = absTestData_TestCaseGenerator
										.get(testData);

								tcG.handleTestCaseDependency(
										targetProjectLoevelDependencyTestData,
										testData, paretnTestCase);
							}

							projectLevelDependencyTestDataList.remove(testData);
							testData.setGenerateTestCaseFinish(true);

							break;
						}
					}
				}
			}
			if (this.projectLevelDependencyTestDataList.size() == 0) {
				break;
			} else if (this.projectLevelDependencyTestDataList.size() == projectLevelDependencyTestDataListSize) {
				System.out
						.println("dead loop in manageProjectLevelMasterAndSlave");
				break;
			}
		}

		loopFlag = true;

		while (loopFlag) {
			int projectLevelDependencyTestDataListSize = this.projectLevelDependencyTestDataList
					.size();

			for (int i = 0; i < simpleFieldLevelDependencyTestDataList.size(); i++) {

				SimpleField simpleField = simpleFieldLevelDependencyTestDataList
						.get(i);

				for (Project project : this.projectList) {
					if (!project.getTestCaseGenerator().isMasterAvailable(
							simpleField)
							&& simpleField.isGloableDependency()) {
						String gloableParentPath = simpleField.getExtendtion()
								.getDependency().getParentPath();

						SimpleField targetProjectLoevelDependencySimpleField = getProjectLevelSimpleField(gloableParentPath);

						if (targetProjectLoevelDependencySimpleField != null) {

							for (Object paretnTestCase : simpleField
									.getTestData().getTestCase()) {

								TestCaseGenerator tcG = absTestData_TestCaseGenerator
										.get(simpleField);

								tcG.handlSimpleFieldDependency(
										targetProjectLoevelDependencySimpleField,
										simpleField, paretnTestCase);
							}

							simpleFieldLevelDependencyTestDataList
									.remove(simpleField);
							simpleField.setGenerateTestCaseFinish(true);

							break;
						}
					}
				}
			}
			if (this.simpleFieldLevelDependencyTestDataList.size() == 0) {
				break;
			} else if (this.simpleFieldLevelDependencyTestDataList.size() == projectLevelDependencyTestDataListSize) {
				System.out.println("dead loop in handlSimpleFieldDependency");
				break;
			}
		}
	}

	private TestData getProjectLevelTestData(String gloableParentPath) {

		for (Project project : this.projectList) {
			if (gloableParentPath != null
					&& gloableParentPath.startsWith(project
							.getGloableProjectPath())) {
				return getLocalProjectTestData(project, gloableParentPath);
			}
		}
		return null;
	}

	private SimpleField getProjectLevelSimpleField(String gloableParentPath) {

		for (Project project : this.projectList) {
			if (gloableParentPath != null
					&& gloableParentPath.startsWith(project
							.getGloableProjectPath())) {
				return getLocalProjectSimpleField(project, gloableParentPath);
			}
		}
		return null;
	}

	private TestData getLocalProjectTestData(Project project,
			String gloableParentPath) {
		String projectPath = project.getGloableProjectPath();

		String localPath = gloableParentPath.substring(projectPath.length());

		return project.getBomGernerator().getPathTestDataMap().get(localPath);
	}

	private SimpleField getLocalProjectSimpleField(Project project,
			String gloableParentPath) {
		String projectPath = project.getGloableProjectPath();

		String localPath = gloableParentPath.substring(projectPath.length());

		return project.getBomGernerator().getPathSimpleFieldMap()
				.get(localPath);
	}

	// 今后BomGenerator要分出来项目主从和内部主从
	private void buildProjectLevelTestCaseDependencyList() {

		for (Project project : this.projectList) {

			for (TestData td : project.getBomGernerator()
					.getDependencyTestDataList()) {
				if (td.isGloableDependency()) {
					projectLevelDependencyTestDataList.add(td);

					absTestData_TestCaseGenerator.put(td,
							project.getTestCaseGenerator());
				}
			}
		}
	}

	private void buildProjectLevelSimpleFieldDependencyList() {

		for (Project project : this.projectList) {

			for (SimpleField simpleField : project.getBomGernerator()
					.getDependencySimpleFieldList()) {
				if (simpleField.isGloableDependency()) {
					simpleFieldLevelDependencyTestDataList.add(simpleField);

					absTestData_TestCaseGenerator.put(simpleField,
							project.getTestCaseGenerator());
				}
			}
		}
	}

	public List<SimpleField> getProjectLevelOutputSimpleFieldList() {

		if (projectLevelOutputSimpleFieldList == null) {
			List<SimpleField> rtn = new ArrayList<SimpleField>();

			for (Project project : this.getProjectList()) {

				if (project.getOutputSimpleFieldList() != null) {
					rtn.addAll(project.getOutputSimpleFieldList());
				}
			}

			projectLevelOutputSimpleFieldList = rtn;
		}

		return projectLevelOutputSimpleFieldList;
	}

	private Map<String, Integer> noteInfoProcessingMap = new HashMap<String, Integer>();

	private Map<String, Double> scoreMap = new HashMap<String, Double>();

	private void clearResultReportMap() {
		noteInfoProcessingMap.put("N000000", 0);
		noteInfoProcessingMap.put("N010000", 0);
		noteInfoProcessingMap.put("N020000", 0);
		noteInfoProcessingMap.put("N030000", 0);
		noteInfoProcessingMap.put("N040000", 0);
		noteInfoProcessingMap.put("N050000", 0);
		noteInfoProcessingMap.put("N060000", 0);
		noteInfoProcessingMap.put("N060100", 0);
		noteInfoProcessingMap.put("N070000", 0);
		noteInfoProcessingMap.put("N080000", 0);
		noteInfoProcessingMap.put("N090000", 0);
		noteInfoProcessingMap.put("N100000", 0);
		noteInfoProcessingMap.put("N110000", 0);
		noteInfoProcessingMap.put("N120000", 0);
		noteInfoProcessingMap.put("N123000", 0);
		noteInfoProcessingMap.put("N127000", 0);
		noteInfoProcessingMap.put("N130000", 0);
		noteInfoProcessingMap.put("N140000", 0);
		noteInfoProcessingMap.put("N140001", 0);
		noteInfoProcessingMap.put("N150000", 0);
		noteInfoProcessingMap.put("N160000", 0);
		noteInfoProcessingMap.put("N170000", 0);
		noteInfoProcessingMap.put("N170001", 0);
		noteInfoProcessingMap.put("N180000", 0);
		noteInfoProcessingMap.put("N500000", 0);
		noteInfoProcessingMap.put("N550000", 0);
		noteInfoProcessingMap.put("N600000", 0);
		noteInfoProcessingMap.put("N700000", 0);
		noteInfoProcessingMap.put("N800000", 0);
		noteInfoProcessingMap.put("N980000", 0);
		noteInfoProcessingMap.put("N990000", 0);

		scoreMap.put("Score01", 0d);
		scoreMap.put("Score03", 0d);
		scoreMap.put("Score04", 0d);
		scoreMap.put("Score05", 0d);
	}

	private void recordScore(Element rskDecisionGlobalEle){
		
		Element scoreModelRtnInfo = (Element)rskDecisionGlobalEle.selectSingleNode("/Application/RiskDecisionGlobal");
		
		if(scoreModelRtnInfo == null){
			return;
		}
		
		if( scoreModelRtnInfo.attributeValue("Score01") != null ){
			scoreMap.put("Score01", Double.parseDouble( scoreModelRtnInfo.attributeValue("Score01") ) );
		}
		
		if( scoreModelRtnInfo.attributeValue("Score03") != null ){
			scoreMap.put("Score03", Double.parseDouble( scoreModelRtnInfo.attributeValue("Score03") ) );
		}
		
		if( scoreModelRtnInfo.attributeValue("Score04") != null ){
			scoreMap.put("Score04", Double.parseDouble( scoreModelRtnInfo.attributeValue("Score04") ) );
		}
		
		if( scoreModelRtnInfo.attributeValue("Score05") != null ){
			scoreMap.put("Score05", Double.parseDouble( scoreModelRtnInfo.attributeValue("Score05") ) );
		}
	}
	
	public RIDOInstance doProecssingTestCase(RIDOInstance ridoInstance) throws Exception {

		clearResultReportMap();

		List<TestCaseInstance> rtnList = new Vector<TestCaseInstance>();

		this.manageProjectLevelMasterAndSlave();

		String frdBlazeOutput = null;

		String rskBlazeOutput = null;

		String oprBlazeOutput = null;

		TestCaseInstance frdTestCaseInstance = ridoInstance.getRidoResultList().get(0);
		Element frdEle = (Element) frdTestCaseInstance.getSrcInput();
		frdTestCaseInstance.setAppID(getApplicationID(frdEle));


		Element fraudDecisionGlobalEle = null;

		TestCaseInstance rskTestCaseInstance = ridoInstance.getRidoResultList().get(1);
		Element rskEle = (Element) rskTestCaseInstance.getSrcInput();
		rskTestCaseInstance.setAppID(getApplicationID(rskEle));


		Element rskDecisionGlobalEle = null;

		TestCaseInstance oprTestCaseInstance = ridoInstance.getRidoResultList().get(2);
		Element oprEle = (Element) oprTestCaseInstance.getSrcInput();
		oprTestCaseInstance.setAppID(getApplicationID(oprEle));


		StringBuffer frdBF = new StringBuffer();

		StringBuffer rskBF = new StringBuffer();

		StringBuffer oprBF = new StringBuffer();

		boolean proecssingRIDOFlag = false;

		int loopFlag = 0;
		
		do {
			
			loopFlag ++;

			frdBlazeOutput = BlazeServers.invokeFrdBlazeServer(frdEle.asXML());

			bufferAppend(frdBF, frdEle, frdBlazeOutput);

			frdEle = DocumentHelper.parseText(frdBlazeOutput).getRootElement();

			fraudDecisionGlobalEle = getFraudDecisionGlobal(frdEle);

			// if( rskCaseList != null && rskCaseList.size()>0 ){

			if (fraudDecisionGlobalEle != null) {

				Element oldFrandDecisionGlobalEle = (Element) rskEle
						.selectSingleNode("/Application/FraudDecisionGlobal");

				if (oldFrandDecisionGlobalEle != null) {
					rskEle.remove(oldFrandDecisionGlobalEle);
				}

				rskEle.add(fraudDecisionGlobalEle);
			} else {
				rskEle.addElement("FraudDecisionGlobal");
			}

			rskBlazeOutput = BlazeServers.invokeRskBlazeServer(rskEle.asXML());

			
			bufferAppend(rskBF, rskEle, rskBlazeOutput);

			rskEle = DocumentHelper.parseText(rskBlazeOutput).getRootElement();
			
			// }

			rskDecisionGlobalEle = getRiskDecisionGlobal(rskEle);
			
			
			if (fraudDecisionGlobalEle != null) {

				Element oldFrandDecisionGlobalEle = (Element) oprEle
						.selectSingleNode("/Application/FraudDecisionGlobal");

				if (oldFrandDecisionGlobalEle != null) {
					oprEle.remove(oldFrandDecisionGlobalEle);
				}

				oprEle.add(fraudDecisionGlobalEle.createCopy());
			} else {
				oprEle.addElement("FraudDecisionGlobal");
			}

			if (rskDecisionGlobalEle != null) {

				Element oldFrandDecisionGlobalEle = (Element) oprEle
						.selectSingleNode("/Application/RiskDecisionGlobal");

				if (oldFrandDecisionGlobalEle != null) {
					oprEle.remove(oldFrandDecisionGlobalEle);
				}

				oprEle.add(rskDecisionGlobalEle);
			} else {
				oprEle.addElement("RiskDecisionGlobal");
			}

			oprBlazeOutput = BlazeServers.invokeOprBlazeServer(rskEle.asXML());

			bufferAppend(oprBF, oprEle, oprBlazeOutput);

			oprEle = DocumentHelper.parseText(oprBlazeOutput).getRootElement();

			proecssingRIDOFlag = IsOPRBlazeOutputFinalDecision(oprEle, loopFlag);

			RIDOPrcessing(frdEle, rskEle, oprEle, oprBF, ridoInstance);

			operationBlazeoutput(frdEle);

			operationBlazeoutput(rskEle);

			operationBlazeoutput(oprEle);

			if (proecssingRIDOFlag) {
				frdTestCaseInstance.setFinalOutput(frdBlazeOutput);

				rskTestCaseInstance.setFinalOutput(rskBlazeOutput);

				oprTestCaseInstance.setFinalOutput(oprBlazeOutput);
				
				Element oprBlazeOutputFinalEle = DocumentHelper.parseText(
						oprBlazeOutput).getRootElement();
				
				recordScore(oprBlazeOutputFinalEle);
				
				
				Attribute finalDecisionResultAttr = (Attribute) oprBlazeOutputFinalEle
						.selectSingleNode("/Application/OprDecisionGlobal/@FinalDecisionResult");

				String finalResult = finalDecisionResultAttr.getValue();

				oprTestCaseInstance.setFinalResult(finalResult);
			}
			// }
		} while (!proecssingRIDOFlag);

		rskTestCaseInstance.setContent(rskBF.toString());

		frdTestCaseInstance.setContent(frdBF.toString());

		oprTestCaseInstance.setContent(oprBF.toString());

		rtnList.add(frdTestCaseInstance);

		rtnList.add(rskTestCaseInstance);

		rtnList.add(oprTestCaseInstance);



		ridoInstance.setNodeProcessingMap(noteInfoProcessingMap);

		ridoInstance.setScoreMap(scoreMap);

		ridoInstance.setRidoResultList(rtnList);

		ridoInstance.setAppID(oprTestCaseInstance.getAppID());
		
		frdTestCaseInstance.getAppID();
		
		if(loopFlag >= 50){
			ridoInstance.setFinalResult("Drop");
		}
		
		return ridoInstance;
	}

	public String getApplicationID(Element blazeOutput) {
		Attribute attr = (Attribute) blazeOutput
				.selectSingleNode("/Application/ApplicationForm[1]/@ApplicationID");
		return attr.getValue();
	}

	private boolean IsOPRBlazeOutputFinalDecision(Element oprElement, int loopFlag) {

		Attribute finalDecisionResultAttr = (Attribute) oprElement
				.selectSingleNode("/Application/OprDecisionGlobal/@FinalDecisionResult");

		String curNodeID = oprElement.attributeValue("CurNodeID");
		
		if(curNodeID != null){
			
			if( loopFlag >= 50 ){
				writeXML(oprElement, oprElement.asXML(), "C:/temp/xml");
				return true;
			}
			
			this.noteInfoProcessingMap.put(curNodeID, this.noteInfoProcessingMap.get(curNodeID) + 1);
		
			if ("N990000".equals( curNodeID ) || "N980000".equals( curNodeID )) {
				return true;
			}
		}
		
		return false;
	}

	
	private void RIDOPrcessing(Element frdEle, Element rskEle, Element oprEle,
			StringBuffer oprBF, RIDOInstance rtnInstance) throws Exception {

		String currentNode = oprEle.attributeValue("CurNodeID");

		Element targetNextNodeElement = (Element) oprEle
				.selectSingleNode("/Application/NodeInfo[@NodeID='"
						+ currentNode + "']");

		String nextNodeId = targetNextNodeElement.attributeValue("NextNodeID");

		if("N600000".equals(nextNodeId)){
			recordNode60000(oprEle, currentNode, rtnInstance);
		}
		
		frdEle.setAttributeValue("CurNodeID", nextNodeId);

		rskEle.setAttributeValue("CurNodeID", nextNodeId);

		oprEle.setAttributeValue("CurNodeID", nextNodeId);
	}
	
	public static Map<String,Map> node60000RecordMap = new HashMap<String, Map>();
	
	private synchronized void recordNode60000(Element ele, String priNodeID, RIDOInstance rtnInstance){
		if(node60000RecordMap.get(priNodeID) == null ){
			Map<String, Integer> nodeDetailMap = new HashMap<String, Integer>();
			node60000RecordMap.put(priNodeID, nodeDetailMap);
		}
		Map<String, Integer> preNodeRecordMap = node60000RecordMap.get(priNodeID);
		
		Element ruleDecisionElement = (Element) ele
				.selectSingleNode("/Application/DecisionResponse/RuleDecision");
		
		String ruleCode = ruleDecisionElement.attributeValue("RuleCode");
		
		String ruleName = ruleDecisionElement.attributeValue("RuleName");
		
		if(preNodeRecordMap.get(ruleCode) == null){
			preNodeRecordMap.put(ruleCode, 0);
		}
		
		preNodeRecordMap.put(ruleCode, preNodeRecordMap.get(ruleCode) + 1);
		
		rtnInstance.setN60000RuleName(ruleName);
		rtnInstance.setPreN60000Node(priNodeID);
	}

	private Element getFraudDecisionGlobal(Element frdBlazeOutput) {

		return getElementCreateCopy(frdBlazeOutput, "FraudDecisionGlobal");
	}

	private Element getRiskDecisionGlobal(Element frdBlazeOutput) {

		return getElementCreateCopy(frdBlazeOutput, "RiskDecisionGlobal");
	}

	private Element getElementCreateCopy(Element appEle,
			String applicationFirstChildEleName) {

		Element fraudDecisionGlobalEle = (Element) appEle
				.selectSingleNode("//Application/"
						+ applicationFirstChildEleName);

		if (fraudDecisionGlobalEle != null) {
			return fraudDecisionGlobalEle.createCopy();
		}

		return null;
	}

	private void operationBlazeoutput(Element outputEle) {
		Node responseEle = outputEle
				.selectSingleNode("//Application/DecisionResponse");

		Node processingHistoryEle = outputEle
				.selectSingleNode("//Application/ProcessingHistory");

		Node messageListEle = outputEle
				.selectSingleNode("//Application/MessageList");

		Node fraudDecisionGlobalEle = outputEle
				.selectSingleNode("//Application/FraudDecisionGlobal");

		Node riskDecisionGloballEle = outputEle
				.selectSingleNode("//Application/RiskDecisionGlobal");

		Node oprDecisionGloballEle = outputEle
				.selectSingleNode("//Application/OprDecisionGlobal");

		if (responseEle != null) {
			outputEle.remove(responseEle);
		}

		if (processingHistoryEle != null) {
			outputEle.remove(processingHistoryEle);
		}

		if (messageListEle != null) {
			outputEle.remove(messageListEle);
		}

		// if(fraudDecisionGlobalEle != null){
		// outputEle.remove(fraudDecisionGlobalEle);
		// }
		//
		// if(riskDecisionGloballEle != null){
		// outputEle.remove(riskDecisionGloballEle);
		// }
		//
		// if(oprDecisionGloballEle != null){
		// outputEle.remove(oprDecisionGloballEle);
		// }
		//
	}

	private void bufferAppend(StringBuffer bf, Element ele, String output)
			throws Exception {
		String curNode = ele.attributeValue("CurNodeID");

		// 设置文件编码
		OutputFormat xmlFormat = new OutputFormat();
		xmlFormat.setEncoding("UTF-8");
		// 设置换行
		xmlFormat.setNewlines(true);
		// 生成缩进
		xmlFormat.setIndent(true);
		// 使用4个空格进行缩进, 可以兼容文本编辑器
		xmlFormat.setIndent("    ");

		StringWriter writer = new StringWriter();
		// 格式化输出流
		XMLWriter xmlWriter = new XMLWriter(writer, xmlFormat);
		// 将document写入到输出流
		xmlWriter.write(ele.getDocument());
		xmlWriter.close();

		bf.append("blaze input in node : ").append(curNode).append("\n");
		bf.append(writer.toString());

		bf.append("blaze output in node : ").append(curNode).append("\n");
		bf.append(output);

		bf.append("------------------------------------------------------------------------------------------------");
	}

	private void writeXML(Element ele, String blazeOutput, String filePath) {
		 //创建字符串缓冲区
		 StringWriter stringWriter = new StringWriter();
		 //设置文件编码
		 OutputFormat xmlFormat = new OutputFormat();
		 xmlFormat.setEncoding("UTF-8");
		 // 设置换行
		 xmlFormat.setNewlines(true);
		 // 生成缩进
		 xmlFormat.setIndent(true);
		 // 使用4个空格进行缩进, 可以兼容文本编辑器
		 xmlFormat.setIndent("    ");
		
		 Document doc = ele.getDocument();
		
		 filePath = filePath + Math.random() + ".xml";
		
		 try{
		 Writer fileWriter = new FileWriter( filePath, true );
		
		 //创建写文件方法
		 XMLWriter xmlWriter = new XMLWriter(fileWriter,xmlFormat);
		
		 fileWriter.write("blaze input in node : " +
		 ele.attributeValue("CurNodeID") + "\n");
		
		 //写入文件
		 xmlWriter.write(doc);
		
		 fileWriter.write("blaze output in node : " +
		 ele.attributeValue("CurNodeID") + "\n");
		
		 fileWriter.write(blazeOutput);
		
		 fileWriter.write("-----------------------------------------------------------------------------------------------------------------------------------------\n");
		
		 //关闭
		 xmlWriter.close();
		
		 fileWriter.close();
		
		 // 输出xml
		 }
		 catch(Exception e){
		 e.printStackTrace();
		 }

	}

	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		systemInitialize();
	}

	private void systemInitialize() {
		// loadAllProjectsFromDB();
	}

	private void loadAllProjectFromRMAFileStorageLocation() {
		String fileStorageLocation = ResourceBundle.getBundle("config")
				.getString("FILE_STORAGE_LOCATION");

		File fileStorageLocationDir = new File(fileStorageLocation);

		File[] fileStorageLocationDirs = fileStorageLocationDir.listFiles();

		String[] projectNameArr = new String[fileStorageLocationDirs.length];

		for (int i = 0; i < fileStorageLocationDirs.length; i++) {
			projectNameArr[i] = fileStorageLocationDirs[i].getName();
		}

		if (this.projectList.size() > projectNameArr.length) {
			mergeProject(fileStorageLocationDirs);
		} else if (this.projectList.size() == projectNameArr.length) {

		}
	}

	private void mergeProject(File[] fileStorageLocationDirs) {

		// for(File projectDir : fileStorageLocationDirs){
		// String projectDirFileName = projectDir.getName();
		// boolean isExits = false;
		// for(Project project:this.projectList){
		// if( project.getProjectName().equals(projectDirFileName) ){
		// isExits = true;
		// break;
		// }
		// }
		//
		// if( ! isExits){
		// Project newProject = new Project();
		//
		// newProject.setProjectName(projectDirFileName);
		//
		// newProject.setProjectType(0);
		//
		// newProject.setServerFilePath( getServerFilePath( projectDir ) );
		//
		// this.projectService.saveProject(newProject);
		// }
		// }
	}

	private String getServerFilePath(File rmaAdbFileDri) {
		for (File adbFileDir : rmaAdbFileDri.listFiles()) {
			if ("adb".equals(adbFileDir.getName())) {
				File[] adbFileArr = adbFileDir.listFiles();

				for (File _serFile : adbFileArr) {
					if (_serFile.getName().endsWith("_ser.server")) {
						return _serFile.getAbsolutePath();
					}
				}
			}
		}
		return null;
	}

	// private void loadAllProjectsFromDB(){
	// List<Project> projectList = this.projectService.listAllProject();
	//
	// this.projectList = projectList;
	//
	// for(Project project : this.projectList){
	// loadAllTestData(project.getProjectID());
	// }
	// }
	//
	private void loadAllTestData(Long projectID) {

	}
}
