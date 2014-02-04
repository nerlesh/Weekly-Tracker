package resource.dataobject.teamlead;

import java.io.Serializable;

public class UserObject implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = -7022145453437093148L;
String userName;
String group;
String email;
String designation;
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getGroup() {
	return group;
}
public void setGroup(String group) {
	this.group = group;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getDesignation() {
	return designation;
}
public void setDesignation(String designation) {
	this.designation = designation;
}

}
