package com.fico.testCaseGenerator.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fico.testCaseGenerator.configuration.Dependency;
import com.fico.testCaseGenerator.configuration.Extendtion;
import com.fico.testCaseGenerator.configuration.Item;
import com.fico.testCaseGenerator.configuration.Restriction;

public class LoadConfiguration {

	private String projectName;
	
	public LoadConfiguration(String projectName){
		this.projectName = projectName;
	}
	
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	@SuppressWarnings("deprecation")
	public static List<Extendtion> readExcelData(String fileName) {
		List<Extendtion> extendtionList = new ArrayList<Extendtion>();
		int parentAndNodeRowNumber = 0;
		int fieldNameRowNumber = 0;
		int parentPathRowNumber = 0;
		int deprecationRowNumber = 0;
		int deprecationFunctionName =0;
		int inputOrOutputRowNumber = 0;
		int intervalRowNumber = 0;
		int nullPercentageRowNumber = 0;
		int minValueRowNumber = 0;
		int firstCodomainRowNumber = 0;
		
		try {
			// Create the input stream from the xlsx/xls file
			FileInputStream fis = new FileInputStream(fileName);
			// Create Workbook instance for xlsx/xls file input stream
			Workbook workbook = null;
			if (fileName.toLowerCase().endsWith("xlsx")) {
				workbook = new XSSFWorkbook(fis);
			} else if (fileName.toLowerCase().endsWith("xls")) {
				workbook = new HSSFWorkbook(fis);
			}
			// Get the number of sheets in the xlsx file
			int numberOfSheets = workbook.getNumberOfSheets();
			// loop through each of the sheets
			for (int i = 0; i < numberOfSheets; i++) {
				String sheetName=workbook.getSheetName(i); 
				if (sheetName.equalsIgnoreCase("DecisionRequest")){
				// Get the nth sheet from the workbookt
				Sheet sheet = workbook.getSheetAt(i);
				
				
				for(int r=0 ;r<=1;r++){
					HSSFRow row = (HSSFRow) sheet.getRow(r);
					for (int c = 0; c < row.getLastCellNum(); c++){
						Cell cell = row.getCell(c);
						if(cell.toString().trim().equalsIgnoreCase("JAVA Node")){
							parentAndNodeRowNumber = c;
						}else if(cell.toString().trim().equalsIgnoreCase("Field Name")){
							fieldNameRowNumber = c;
						}else if(cell.toString().trim().equalsIgnoreCase("Path")){
							parentPathRowNumber = c;
						}else if(cell.toString().trim().equalsIgnoreCase("主从关系")){
							deprecationRowNumber = c;
						}else if(cell.toString().trim().equalsIgnoreCase("函数名（当主从关系为3时有值）")){
							deprecationFunctionName = c;
						}else if (cell.toString().trim().equalsIgnoreCase("输入/输出(默认输入)")){
							inputOrOutputRowNumber = c;
						}else if (cell.toString().trim().equalsIgnoreCase("区间类型")){
							intervalRowNumber = c;
						}else if(cell.toString().trim().equalsIgnoreCase("空值所占百分比")){
							nullPercentageRowNumber = c;
						}else if(cell.toString().trim().equalsIgnoreCase("最小值")){
							minValueRowNumber = c;
						}else if (cell.toString().trim().equalsIgnoreCase("值域")){
							firstCodomainRowNumber = c;
							break;
						}
					}
				}
				
				
				

				Map<Integer,String> realPathMap=getRealPath(sheet,parentAndNodeRowNumber+1);
				
				for (int r = 2; r <= sheet.getLastRowNum(); r++) {
					HSSFRow row = (HSSFRow) sheet.getRow(r);
					String realPath = "";
					Extendtion extendtion = null;
					Dependency dependency = null;
					Restriction restriction =null;
					Item  item = null;
					List<Item> itemList = new ArrayList<Item>();
					for (int c = 0; c < row.getLastCellNum(); c++) {
						Cell cell = row.getCell(c);
						if (c == fieldNameRowNumber) {
							realPath=	realPathMap.get(r);
							if (cell!=null&&cell.getCellType() == Cell.CELL_TYPE_STRING) {
								realPath=realPath+ "@"+cell.getStringCellValue();
							}
						 
						}
						if (c == parentPathRowNumber) {
							if (cell!=null&&cell.getCellType() == Cell.CELL_TYPE_STRING) {
								dependency = new Dependency();
								//modified by yuanxb
								dependency.setParentPath( cell.getStringCellValue() );
							}
							// System.out.print(cell.getStringCellValue());
						} else if (c == deprecationRowNumber) {
							if (cell!=null&&cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
								if (cell.getNumericCellValue() == 0) {
									dependency.setType(Dependency.getTYPE_NULL());
								} else if (cell.getNumericCellValue() == 1) {
									dependency.setType(Dependency.getTYPE_NUMBER());
								} else if (cell.getNumericCellValue() == 2) {
									dependency.setType(Dependency.getTYPE_EQUICALENCE());
								} else if (cell.getNumericCellValue() == 3) {
									dependency.setType(Dependency.getTYPE_FUNCTIONNAME());
								}

							}
						} else if (c == deprecationFunctionName) {

							if (cell!=null&&cell.getCellType() == Cell.CELL_TYPE_STRING) {
								dependency.setFunctionName(cell
										.getStringCellValue());
							}
						} 

						if(c==inputOrOutputRowNumber){
							if (cell!=null&&cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
								restriction =new Restriction();
								if(restriction!=null){
									restriction.setTransfersType(cell.getNumericCellValue()); 
								}
							}
						}
						if(c == intervalRowNumber){
							if (cell!=null&&cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
								if(restriction!=null){
									if(cell.getNumericCellValue() == 1){
									restriction.setType(restriction.TYPE_ENMURATION);  
									}else if(cell.getNumericCellValue() == 2){
										restriction.setType(restriction.TYPE_SECTION);
									}else if(cell.getNumericCellValue() == 3){
										restriction.setType(restriction.TYPE_FUNCTION);
									}else{
										restriction.setType(cell.toString());
									}
								}
								
							}
							
						}
						

						if (c == nullPercentageRowNumber) {
							
							if (cell!=null&&cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
								if(restriction!=null){
								restriction.setNullPercentage(cell.getNumericCellValue()); 
								}
							}
						} 
						
						if (c == minValueRowNumber) {
							
							if (cell!=null) {
								if(restriction!=null){
									
								restriction.setMinStr(cell.toString());
								
								}
							}
						} else if (c >= firstCodomainRowNumber) {
							int num=firstCodomainRowNumber%2;
							int mod=c%2;
							
							if(mod==num){
								item= new Item();
							}
							if(mod==num){
								String cellVal = null;
								if (cell!=null&&cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
									cell.setCellType(HSSFCell.CELL_TYPE_STRING);
									cellVal = cell.getStringCellValue();
								}
								else{
									cellVal = cell.toString();
								}
								
								if(cellVal != null && !"".equals(cellVal)){
									item.setValue(cellVal);
								}
							}else{
								
								if(cell != null &&  null!=cell.toString() && ""!=cell.toString()  && null!=item.getValue() && !"".equals(item.getValue().trim())){
									
									item.setPercentage(Double.parseDouble(cell.toString()));
									
								}
								
								
								if(item.getPercentage() != null 
										&& item.getValue() != null 
										&& !"".equals(item.getValue())){
									itemList.add(item);
								}
							}
						}
						
						if(restriction !=null){
							restriction.setItem(itemList);
						}
					}
					
					if(restriction !=null || dependency!= null||r ==2){
						extendtion = new Extendtion();
						extendtion.setRealPath(realPath);
						extendtion.setName("dependency");
					}
					if(restriction !=null ){
						extendtion.setRestriction(restriction);
					}
					if(dependency!= null){
						extendtion.setDependency(dependency);
					}
					if(extendtion!=null){
					extendtionList.add(extendtion);
					}

				} // end of sheets for loop

				fis.close();
			}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return extendtionList;
	}

	public static void main(String args[]) {
		//广州银行
		List<Extendtion> list = readExcelData("C:\\FICO\\CAMS\\bom\\test.xls");
		
		System.out.println("111111111111");
	}

	public Map<String,Extendtion>   loadConfiguration(){
		String filePath ="BOM/"+projectName+"/"+projectName+".xlsx";
		
		List<Extendtion> list = readExcelData(filePath);
		Map<String,Extendtion> map = new HashMap<String, Extendtion>();
		for(int i=0;i<list.size();i++){
			map.put(list.get(i).getRealPath(), list.get(i));
		}
		return map;
	}

	public static Map<Integer,String> getRealPath(Sheet sheet,int strucCellNumber){
		String  realPath="";
		int lastRowNumber=sheet.getLastRowNum();
		Map<Integer,String> map = new HashMap<Integer,String>();
		
		for(int r=2;r<lastRowNumber;r++){
			int startRowNumber=2;
			realPath="";
			HSSFRow row = (HSSFRow) sheet.getRow(r);
			String element = null;
			String cellstr=null;
			for(int c=0;c<strucCellNumber;c++){
				element="";
				Cell cell=row.getCell(c);
				if(cell!=null){
				 cellstr=cell.toString();
				}
				if(cell==null||cellstr.endsWith("")){
					if(c>0){
						for(int cr=r;cr>=2;cr--){
							HSSFRow row2 = (HSSFRow) sheet.getRow(cr);
							Cell cell2=row2.getCell(c-1);
							Cell cell3 = null;
							if(c>=3){cell3=row2.getCell(c-2);}
							if(cell2!=null&&cell2.getCellType()==Cell.CELL_TYPE_STRING){
								startRowNumber=cr;
								break;
							}
							if(cell3!=null&&cell3.getCellType()==Cell.CELL_TYPE_STRING){
								startRowNumber=cr;
								break;
							}
						}
					}
					for(int cr=startRowNumber;cr<=r;cr++){
						HSSFRow row2 = (HSSFRow) sheet.getRow(cr);
						Cell cell2=row2.getCell(c);
						if(cell2!=null&&cell2.getCellType()==Cell.CELL_TYPE_STRING){
							element =cell2.getStringCellValue().trim()+"/";
						}
					}
				}else if(cell!=null&&cell.getCellType()==Cell.CELL_TYPE_STRING){
					element = cell.getStringCellValue().trim()+"/";
				}	
				realPath=realPath+element;
			}
			
			map.put(r, realPath);
		}
		
		
		return map;
		
		
		
	}
	
	
	public static List<String> listAllProjectsName(){
		
		List<String> ListProject = new ArrayList<String>();
		File root = new File("C:\\projects\\testCaseGenerator\\eclipse_work_space\\TestCaseGeneratorWeb\\BOM");
	    File[] files = root.listFiles();
	    for(File file:files){     
	    	if(file.isDirectory()){

	    		ListProject.add(file.getName());
	      
	    	}   
	    }
		return ListProject;
	}
}