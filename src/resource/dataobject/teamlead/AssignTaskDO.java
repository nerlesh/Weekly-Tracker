package resource.dataobject.teamlead;

import java.io.Serializable;

public class AssignTaskDO implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = -6281208853549573529L;
String date;
String item;
String activity;
String isPartofAssignedTask;
String description;
String hoursSpent;
String userId;
/**
 * @return the date
 */
public String getDate() {
	return date;
}
/**
 * @param date the date to set
 */
public void setDate(String date) {
	this.date = date;
}
/**
 * @return the item
 */
public String getItem() {
	return item;
}
/**
 * @param item the item to set
 */
public void setItem(String item) {
	this.item = item;
}
/**
 * @return the activity
 */
public String getActivity() {
	return activity;
}
/**
 * @param activity the activity to set
 */
public void setActivity(String activity) {
	this.activity = activity;
}
/**
 * @return the isPartofAssignedTask
 */
public String getIsPartofAssignedTask() {
	return isPartofAssignedTask;
}
/**
 * @param isPartofAssignedTask the isPartofAssignedTask to set
 */
public void setIsPartofAssignedTask(String isPartofAssignedTask) {
	this.isPartofAssignedTask = isPartofAssignedTask;
}
/**
 * @return the description
 */
public String getDescription() {
	return description;
}
/**
 * @param description the description to set
 */
public void setDescription(String description) {
	this.description = description;
}
/**
 * @return the hoursSpent
 */
public String getHoursSpent() {
	return hoursSpent;
}
/**
 * @param hoursSpent the hoursSpent to set
 */
public void setHoursSpent(String hoursSpent) {
	this.hoursSpent = hoursSpent;
}
/**
 * @return the userId
 */
public String getUserId() {
	return userId;
}
/**
 * @param userId the userId to set
 */
public void setUserId(String userId) {
	this.userId = userId;
}
/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {
	return "AssignTaskDO [date=" + date + ", item=" + item + ", activity="
			+ activity + ", isPartofAssignedTask=" + isPartofAssignedTask
			+ ", description=" + description + ", hoursSpent=" + hoursSpent
			+ ", userId=" + userId + "]";
}


	
	
}
