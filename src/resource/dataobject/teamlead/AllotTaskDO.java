package resource.dataobject.teamlead;

import java.io.Serializable;

public class AllotTaskDO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1309075739531272570L;
	String task;
	String resource;
	/**
	 * @return the task
	 */
	public String getTask() {
		return task;
	}
	/**
	 * @param task the task to set
	 */
	public void setTask(String task) {
		this.task = task;
	}
	/**
	 * @return the resource
	 */
	public String getResource() {
		return resource;
	}
	/**
	 * @param resource the resource to set
	 */
	public void setResource(String resource) {
		this.resource = resource;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AllotTaskDO [task=" + task + ", resource=" + resource + "]";
	}
	
	
	

}
