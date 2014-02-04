package resource.excel.teamlead;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import resource.common.teamlead.ResourceUtils;
import resource.dataobject.teamlead.AllotTaskDO;

public class AllotTaskExcel {

	
	public String updateAllotTask(ArrayList <AllotTaskDO> allotTaskList){
		HSSFSheet sheet  = null;
		String status = null;
		
		
		try{
			ResourceUtils resourceUtils = new ResourceUtils();
			String filePath = resourceUtils.getPropertyValue("FILEPATH");
			String pRowValue = resourceUtils.getPropertyValue("ALOTTASKROW");
			String pCellValue = resourceUtils.getPropertyValue("ALOTTASKCELL");
			int prRowCount = 0;
			int prCellCount = 0;
			int intialPrRowCount = 0;
			int intialprCellCount = 0;
			
			if(pRowValue!=null && pCellValue!=null){
				prCellCount = Integer.parseInt(pCellValue);
				prRowCount = Integer.parseInt(pRowValue);
			}
			intialPrRowCount = prRowCount;
			intialprCellCount = prCellCount;
			FileInputStream templatePath = new FileInputStream(new File(filePath));
			 HSSFWorkbook workbook = new HSSFWorkbook(templatePath);
			 HSSFCell cell = null;
			if(allotTaskList!=null){
			for(int i=0;i<allotTaskList.size();i++){
			AllotTaskDO allotTaskDO = allotTaskList.get(i);
			String sheetValue = resourceUtils.getPropertyValue(allotTaskDO.getResource());
			if(sheetValue!=null){
				 sheet = workbook.getSheetAt(Integer.parseInt(sheetValue));
				 }
			
			cell = sheet.getRow(intialPrRowCount).getCell(intialprCellCount);
			
			StringTokenizer str = new StringTokenizer(allotTaskDO.getTask(),",");
			
			while (str.hasMoreElements()){
				cell.setCellValue(str.nextElement().toString());
				prRowCount++;
				cell = sheet.getRow(prRowCount).getCell(prCellCount);
			}
			prRowCount = intialPrRowCount;
			prCellCount = intialprCellCount;
			}
			templatePath.close();
			FileOutputStream outFile =new FileOutputStream(new File("/Users/vcrajendiran/Desktop/Weekly_Tracker_Template2007_update.xls"));
		    workbook.write(outFile);
		    outFile.close();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		status = "success";
		
		return status;
	}
}
