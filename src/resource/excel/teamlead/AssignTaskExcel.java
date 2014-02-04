package resource.excel.teamlead;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.model.Sheet;
import org.apache.poi.hssf.model.Workbook;
import org.apache.poi.hssf.record.formula.functions.Cell;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.sun.rowset.internal.Row;

import resource.common.teamlead.ResourceUtils;
import resource.dataobject.teamlead.AssignTaskDO;
import resource.dataobject.teamlead.ProcessStatusDO;

public class AssignTaskExcel {

	public String updateAssignTask(ArrayList<AssignTaskDO> assignStatusList ,String assoId){
		String status = null;
		try{
			ResourceUtils resourceUtils = new ResourceUtils();
			String filePath = resourceUtils.getPropertyValue("FILEPATH");
			String resourceName = resourceUtils.getPropertyValue(assoId);
			String sheetValue = resourceUtils.getPropertyValue(resourceName);
			String pRowValue = resourceUtils.getPropertyValue("ASSIGNTASKROW");
			String pCellValue = resourceUtils.getPropertyValue("ASSIGNTASKCELL");
			//System.out.println("filePath "+filePath+"sheetValue "+sheetValue+"pRowValue "+pRowValue+"pCellValue "+pCellValue);
			int prRowCount = 0;
			int prCellCount = 0;
			if(pRowValue!=null && pCellValue!=null){
				prCellCount = Integer.parseInt(pCellValue);
				prRowCount = Integer.parseInt(pRowValue);
			}
			
			prRowCount = resourceUtils.getRowCount(prRowCount, Integer.parseInt(sheetValue), filePath, prCellCount+1);
	
			HSSFSheet sheet  = null;
			if(filePath!=null){
			FileInputStream templatePath = new FileInputStream(new File(filePath));
			 HSSFWorkbook workbook = new HSSFWorkbook(templatePath);
			 if(sheetValue!=null){
			 sheet = workbook.getSheetAt(Integer.parseInt(sheetValue));
			 }
			 HSSFCell cell = null;
			if(assignStatusList!=null){
				for(int i=0;i<assignStatusList.size();i++){
					AssignTaskDO assignTaskDO = assignStatusList.get(i);
							
					 cell = sheet.getRow(prRowCount).getCell(prCellCount);
					 cell.setCellValue(assignTaskDO.getDate().toString());
					 prCellCount++;
					 
					 cell = sheet.getRow(prRowCount).getCell(prCellCount);
					 cell.setCellValue(assignTaskDO.getItem().toString());
					 prCellCount++;
					 
					 cell = sheet.getRow(prRowCount).getCell(prCellCount);
					 cell.setCellValue(assignTaskDO.getActivity().toString());
					 prCellCount++;
					 
					 cell = sheet.getRow(prRowCount).getCell(prCellCount);
					 cell.setCellValue(assignTaskDO.getIsPartofAssignedTask().toString());
					 prCellCount++;
					 
					 cell = sheet.getRow(prRowCount).getCell(prCellCount);
					 cell.setCellValue(assignTaskDO.getDescription().toString());
					 prCellCount++;
					 
					 cell = sheet.getRow(prRowCount).getCell(prCellCount);
					 cell.setCellValue(assignTaskDO.getHoursSpent().toString());
					 prCellCount++;
					 
					 prRowCount ++;
					 prCellCount = Integer.parseInt(pCellValue);
							
				}
				templatePath.close();
			     
			    FileOutputStream outFile =new FileOutputStream(new File("/Users/vcrajendiran/Desktop/Weekly_Tracker_Template2007_update.xls"));
			    workbook.write(outFile);
			    outFile.close();
			    status = "sucess";
			}
			 
			}
			applyFormula(filePath);
		}
		catch(Exception e){
			
		}
		return status;
	}
	
	public static void applyFormula(String filePath){
		try{
			FileInputStream templatePath = new FileInputStream(new File("/Users/vcrajendiran/Desktop/Weekly_Tracker_Template2007_update.xls"));
			HSSFWorkbook workbook = new HSSFWorkbook(templatePath);
			HSSFFormulaEvaluator evaluator = new HSSFFormulaEvaluator(workbook);
			HSSFSheet sheet = workbook.getSheetAt(0);
			// Iterate over each row in the sheet
            Iterator rows = sheet.rowIterator(); 
            while( rows.hasNext() ) {           
                HSSFRow row = (HSSFRow) rows.next();
 
                // Iterate over each cell in the row and print out the cell's content
                Iterator cells = row.cellIterator();
                while( cells.hasNext() ) {
                    HSSFCell cell = (HSSFCell) cells.next();
                    if(cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA) {
                        evaluator.evaluate(cell);
                      // evaluator.evaluateInCell(cell);
                    }
                }
            }
            templatePath.close();	
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
