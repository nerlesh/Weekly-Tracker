package resource.dataobject.teamlead;

import java.io.Serializable;

public class LeaveApplyDO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7541414632082303305L;
	String date;
	String leaveType;
	String appliedPortal;
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
	 * @return the leaveType
	 */
	public String getLeaveType() {
		return leaveType;
	}
	/**
	 * @param leaveType the leaveType to set
	 */
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	/**
	 * @return the appliedPortal
	 */
	public String getAppliedPortal() {
		return appliedPortal;
	}
	/**
	 * @param appliedPortal the appliedPortal to set
	 */
	public void setAppliedPortal(String appliedPortal) {
		this.appliedPortal = appliedPortal;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LeaveApplyDO [date=" + date + ", leaveType=" + leaveType
				+ ", appliedPortal=" + appliedPortal + "]";
	}
	
	

}
