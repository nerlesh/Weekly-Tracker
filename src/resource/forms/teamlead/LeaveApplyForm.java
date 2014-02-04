package resource.forms.teamlead;

import java.io.Serializable;

public class LeaveApplyForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3973940716111629590L;
	String date;
	String leaveType;
	String appliedInPortal;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	public String getAppliedInPortal() {
		return appliedInPortal;
	}
	public void setAppliedInPortal(String appliedInPortal) {
		this.appliedInPortal = appliedInPortal;
	}
	
	
}
