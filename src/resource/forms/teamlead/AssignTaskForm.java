package resource.forms.teamlead;

import java.io.Serializable;

public class AssignTaskForm implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 6662203255528727192L;

String items ;

String activity;

String taskDescription;

String hoursSpend;

String isPartOfAssignedTask;



public String getIsPartOfAssignedTask() {
	return isPartOfAssignedTask;
}

public void setIsPartOfAssignedTask(String isPartOfAssignedTask) {
	this.isPartOfAssignedTask = isPartOfAssignedTask;
}

public String getHoursSpend() {
	return hoursSpend;
}

public void setHoursSpend(String hoursSpend) {
	this.hoursSpend = hoursSpend;
}


public String getTaskDescription() {
	return taskDescription;
}

public void setTaskDescription(String taskDescription) {
	this.taskDescription = taskDescription;
}

public String getItems() {
	return items;
}

public void setItems(String items) {
	this.items = items;
}

public String getActivity() {
	return activity;
}

public void setActivity(String activity) {
	this.activity = activity;
}



}
