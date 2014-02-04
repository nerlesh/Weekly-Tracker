package resource.common.teamlead;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.poi.hssf.record.formula.functions.Cell;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ResourceUtils {
	/**
	 * This retrieves value from properties file for the respctive key
	 * @param key
	 * @return
	 */

	public String getPropertyValue(String key){
		String propValue = null;
		try{
			Properties prop = new Properties();
			prop.load(new FileInputStream("/Users/vcrajendiran/Documents/ResourceManagement/ThinWeb/ResourceProperties.properties"));
			propValue =	prop.getProperty(key);
			return propValue;
		}catch(Exception e){
			e.printStackTrace();
		}
		return propValue;
	}
	/**
	 * This method will check for any updated row presented in the sheet and returns empty row  count 
	 * @param prRowCount
	 * @param sheetNumber
	 * @param filePath
	 * @param prCellCount
	 * @return
	 */
	public int getRowCount(int prRowCount,int sheetNumber,String filePath,int prCellCount){
	
		try{
			FileInputStream file = new FileInputStream(new File(filePath));
		 
	    HSSFWorkbook workbook = new HSSFWorkbook(file);
	    HSSFSheet sheet = workbook.getSheetAt(sheetNumber);
	    HSSFCell cell = null;
	    cell = sheet.getRow(prRowCount).getCell(prCellCount);
	    while(cell!=null && !("".equalsIgnoreCase(cell.getStringCellValue().trim()))){
	    prRowCount++; 
	    cell = sheet.getRow(prRowCount).getCell(prCellCount);
	    }
	    file.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return prRowCount;
	}
	
	public void applySummaryFormula(){
		
	}
}
