package resource.common;

import resource.excel.teamlead.ReadAllotedTask;

public class LocalTest {

	
	public static void main(String args[]){
		ReadAllotedTask readAllotedTask = new ReadAllotedTask();
		String assID = "334988";
		readAllotedTask.readAllotedExcel(assID);
	}
}
