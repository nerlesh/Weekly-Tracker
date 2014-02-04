package resource.excel.teamlead;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import resource.common.teamlead.ResourceUtils;
import resource.dataobject.teamlead.LeaveApplyDO;


public class LeaveApplyExcel {

	public String updateLeaveApply (ArrayList <LeaveApplyDO> lApplyList,String assoId){
		String status = null;
		try{

			ResourceUtils resourceUtils = new ResourceUtils();
			String filePath = resourceUtils.getPropertyValue("FILEPATH");
			String resourceName = resourceUtils.getPropertyValue(assoId);
			String sheetValue = resourceUtils.getPropertyValue(resourceName);
			String pRowValue = resourceUtils.getPropertyValue("LEAVEAPPLYROW");
			String pCellValue = resourceUtils.getPropertyValue("LEAVEAPPLYCELL");
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
			if(lApplyList!=null){
				for(int i=0;i<lApplyList.size();i++){
					LeaveApplyDO leaveApplyDO = lApplyList.get(i);
							
					 cell = sheet.getRow(prRowCount).getCell(prCellCount);
					 cell.setCellValue(leaveApplyDO.getDate().toString());
					 prCellCount++;
					 cell = sheet.getRow(prRowCount).getCell(prCellCount);
					 cell.setCellValue(leaveApplyDO.getLeaveType().toString());
					 prCellCount++;
					 cell = sheet.getRow(prRowCount).getCell(prCellCount);
					 cell.setCellValue(leaveApplyDO.getAppliedPortal().toString());
					
					 
					 prRowCount++;
					 prCellCount = Integer.parseInt(pCellValue);
							
				}
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
