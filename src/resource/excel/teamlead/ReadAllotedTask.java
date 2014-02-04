package resource.excel.teamlead;

import java.io.FileInputStream;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import resource.common.teamlead.ResourceUtils;

public class ReadAllotedTask {

	
	
	public ArrayList readAllotedExcel (String assID){
		
		ArrayList resultList = new ArrayList();
		ResourceUtils resourceUtils = new ResourceUtils();
		String filePath = resourceUtils.getPropertyValue("FILEPATH");
		String resourceName = resourceUtils.getPropertyValue(assID);
		String sheetValue = resourceUtils.getPropertyValue(resourceName);
		String pRowValue = resourceUtils.getPropertyValue("ALOTTASKROW");
		String pCellValue = resourceUtils.getPropertyValue("ALOTTASKCELL");
		int prRowCount = 0;
		int prCellCount = 0;
		if(pRowValue!=null && pCellValue!=null){
			prCellCount = Integer.parseInt(pCellValue);
			prRowCount = Integer.parseInt(pRowValue);
		}
		try{
			FileInputStream file = new FileInputStream(filePath);
			HSSFWorkbook workbook = new HSSFWorkbook(file);
			HSSFSheet sheet = workbook.getSheetAt(Integer.parseInt(sheetValue));
			HSSFCell cell = null;
			cell = sheet.getRow(prRowCount).getCell(prCellCount); 
			while(cell!=null && !("".equalsIgnoreCase(cell.getStringCellValue().trim()))){
				resultList.add(cell.getStringCellValue());
				prRowCount++; 
				cell = sheet.getRow(prRowCount).getCell(prCellCount);
			}
			 
			 
			
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(resultList);
		return resultList;
		
	}
}
