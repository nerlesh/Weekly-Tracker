package resource.dataobject.teamlead;

import java.io.Serializable;

public class ProcessStatusDO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3257532076923404573L;

	@Override
	public String toString() {
		return "ProcessStatusDO [items=" + items + ", activity=" + activity
				+ ", count=" + count + ", status=" + status + "]";
	}
	String items;
	String activity;
	String count;
	String status;
	
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
	
	
}
