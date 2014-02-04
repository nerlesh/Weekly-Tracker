package resource.common.teamlead;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import resource.dataobject.teamlead.LoginDO;
import resource.dataobject.teamlead.UserObject;

public class LDAPConnector {

	private static final String CLASS_NAME = "LDAPConnector";
	
	
	@SuppressWarnings("unchecked")
	public static UserObject authenticate(LoginDO loginDO) throws Exception {
		System.out.println("Inside the execute");
		Hashtable<String,String> env = new Hashtable<String,String>(11);
		UserObject userObject = null;

		DirContext ctx = null;
		String LDAP_CONTEXT_QUERY = "OU=India,OU=APAC,OU=Cognizant,dc=cts,dc=com";//LDAP_CONTEXT_QUERY
		try {			
			
				
			String DOMAIN = "CTS";//LDAP_DEFAULT_DOMAIN
			
			String LDAP_URL = "ldap://10.237.5.106:389";//LDAP_URL				
			env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
			env.put(Context.PROVIDER_URL, LDAP_URL);
			env.put(Context.SECURITY_CREDENTIALS, loginDO.getPassword());
			env.put(Context.SECURITY_PRINCIPAL, DOMAIN + "\\" 	+ loginDO.getUserID());
			
			try {
				ctx = new InitialDirContext(env);				
				System.out.println("authenticate Login Successful ");
				SearchControls ctls = new SearchControls();
				String[] attrIDs = { "cn", "mail", "Title", "department","sAMAccountName","displayname" };
				ctls.setReturningAttributes(attrIDs);
				ctls.setReturningObjFlag(true);
				ctls.setSearchScope(SearchControls.SUBTREE_SCOPE);
				String filter = "(&( objectcategory=user)(sAMAccountName="+ loginDO.getUserID() + "))";
				NamingEnumeration answer = ctx.search(LDAP_CONTEXT_QUERY, filter, ctls);
				userObject = formatResults(answer, userObject);			
				ctx.close();
			} catch (NamingException e) {
				
				 System.out.println("Error Message : "+e.getMessage());	
				 e.printStackTrace();
				 
				 throw e;
				 
				
			} 

		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return userObject;
	}

	@SuppressWarnings("unchecked")
	public static UserObject formatResults(NamingEnumeration enumvalue,UserObject userObject) throws Exception {
		
		String name = null;
		String title = null;
		String department = null;
		String mail = null;
		String samAccountName = null;
		Attributes attrs = null;
		String displayname = "";

		while (enumvalue.hasMore()) {
			SearchResult sr = (SearchResult) enumvalue.next();
			attrs = sr.getAttributes();
			for (enumvalue = attrs.getAll(); enumvalue.hasMore();) {
				Attribute attrib = (Attribute) enumvalue.next();
				String tmpStr = attrib.getID();
				if (tmpStr.equalsIgnoreCase("mail")) {
					for (NamingEnumeration e = attrib.getAll(); e.hasMore();) {
						mail = e.next().toString();
					}
				} else if (tmpStr.equalsIgnoreCase("department")) {
					for (NamingEnumeration e = attrib.getAll(); e.hasMore();) {
						department = e.next().toString();
					}
				} else if (tmpStr.equalsIgnoreCase("title")) {
					for (NamingEnumeration e = attrib.getAll(); e.hasMore();) {
						title = e.next().toString();
					}
				} else if (tmpStr.equalsIgnoreCase("cn")) {
					for (NamingEnumeration e = attrib.getAll(); e.hasMore();) {
						name = e.next().toString();
					}
				} else if (tmpStr.equalsIgnoreCase("SAMAccountName")) {
					for (NamingEnumeration e = attrib.getAll(); e.hasMore();) {
						samAccountName = e.next().toString();
					}
				}else if (tmpStr.equalsIgnoreCase("displayname")) {
					for (NamingEnumeration e = attrib.getAll(); e.hasMore();) {
						displayname = e.next().toString();
						System.out.println("displayname --->"+displayname);
					}
				}
			}
		}
		
		if(userObject==null)
			userObject = new UserObject();
		userObject.setUserName(name);
		userObject.setGroup(department);
		userObject.setEmail(mail);
		userObject.setDesignation(title);
		userObject.setUserName(displayname);

		
		return userObject;
	}
	
	public static void main(String[] args) throws Exception {
		LoginDO loginDO = new LoginDO();
		//loginDO.setUserId("111524");
		loginDO.setUserID("334988");
		loginDO.setPassword("sepforever1!");
		UserObject userObject = LDAPConnector.authenticate(loginDO);
		System.out.println(userObject);
	}
	
}
