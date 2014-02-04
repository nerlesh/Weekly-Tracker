package resource.forms.teamlead;

import java.io.Serializable;

public class AllotTaskForm implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 4599522994423756557L;

String resource;

String taskAssigned;

/**
 * @return the taskAssigned
 */
public String getTaskAssigned() {
	return taskAssigned;
}

/**
 * @param taskAssigned the taskAssigned to set
 */
public void setTaskAssigned(String taskAssigned) {
	this.taskAssigned = taskAssigned;
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
	return "AllotTaskForm [resource=" + resource + "]";
}


}
