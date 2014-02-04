package resource.excel.teamlead;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import org.apache.poi.hssf.record.formula.functions.Cell;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import resource.common.teamlead.ResourceUtils;
import resource.dataobject.teamlead.ProcessStatusDO;

public class ProcessStatusExcel {

	public String updateProcessStatus (ArrayList <ProcessStatusDO> pStatusList,String assoId){
		String status = null;
		try{

			ResourceUtils resourceUtils = new ResourceUtils();
			String filePath = resourceUtils.getPropertyValue("FILEPATH");
			String resourceName = resourceUtils.getPropertyValue(assoId);
			String sheetValue = resourceUtils.getPropertyValue(resourceName);
			String pRowValue = resourceUtils.getPropertyValue("PROCESSSTATUSROW");
			String pCellValue = resourceUtils.getPropertyValue("PROCESSSTATUSCELL");
			//System.out.println("filePath "+filePath+"sheetValue "+sheetValue+"pRowValue "+pRowValue+"pCellValue "+pCellValue);
			int prRowCount = 0;
			int prCellCount = 0;
			if(pRowValue!=null && pCellValue!=null){
				prCellCount = Integer.parseInt(pCellValue);
				prRowCount = Integer.parseInt(pRowValue);
			}
			
			prRowCount = resourceUtils.getRowCount(prRowCount, Integer.parseInt(sheetValue), filePath, prCellCount);
	
			HSSFSheet sheet  = null;
			if(filePath!=null){
			FileInputStream templatePath = new FileInputStream(new File(filePath));
			 HSSFWorkbook workbook = new HSSFWorkbook(templatePath);
			 if(sheetValue!=null){
			 sheet = workbook.getSheetAt(Integer.parseInt(sheetValue));
			 }
			 HSSFCell cell = null;
			if(pStatusList!=null){
				for(int i=0;i<pStatusList.size();i++){
					ProcessStatusDO processStatusDO = pStatusList.get(i);
							
					 cell = sheet.getRow(prRowCount).getCell(prCellCount);
					 cell.setCellValue(processStatusDO.getItems().toString());
					 prCellCount++;
					 cell = sheet.getRow(prRowCount).getCell(prCellCount);
					 cell.setCellValue(processStatusDO.getActivity().toString());
					 prCellCount++;
					 cell = sheet.getRow(prRowCount).getCell(prCellCount);
					 cell.setCellValue(processStatusDO.getCount().toString());
					 prCellCount++;
					 cell = sheet.getRow(prRowCount).getCell(prCellCount);
					 cell.setCellValue(processStatusDO.getStatus().toString());
					 
					 prRowCount++;
					 prCellCount = Integer.parseInt(pCellValue);
							
				}
				resourceUtils.applySummaryFormula();
				templatePath.close();
			     
			    FileOutputStream outFile =new FileOutputStream(new File("/Users/vcrajendiran/Desktop/Weekly_Tracker_Template2007_update.xls"));
			    workbook.write(outFile);
			    outFile.close();
			    status = "sucess";
			}
			 
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return status;
	}
	
	
	
	
}
