package com.fico.testCaseGenerator.dao;

import java.util.List;
import java.util.Map;


import com.fico.testCaseGenerator.testCase.TestCaseInstance;


public interface ExecutionDao {

	public void saveTestCaseInstance(List<TestCaseInstance> testCaseInstanceList);

	public void updateNodeProcessingMap( Map<String, Integer> nodeMap);
	
	public void singleUpdateNodeProcessingMap(String nodeInfo, int times);
	
	public void savescorerecord(String appid, double score1, double score3, double score4, double score5);
}
