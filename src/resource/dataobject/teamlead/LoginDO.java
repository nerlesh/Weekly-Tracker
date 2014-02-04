package resource.dataobject.teamlead;

import java.io.Serializable;

public class LoginDO implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = -1059094990417754361L;
String userID;
String password;
public String getUserID() {
	return userID;
}
public void setUserID(String userID) {
	this.userID = userID;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
}
