package resource.forms.teamlead;

import java.io.Serializable;

public class ProcessStatusForm implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6536689241720370228L;
	String items;
	String activity;
	String count;
	String status;
	String updateFlag;
	
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
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUpdateFlag() {
		return updateFlag;
	}
	public void setUpdateFlag(String updateFlag) {
		this.updateFlag = updateFlag;
	}
	
	
	
	
	
}
