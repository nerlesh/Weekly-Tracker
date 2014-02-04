package resource.actions.teamlead;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import resource.common.teamlead.ResourceUtils;

public class LoginAction extends Action{
	public ActionForward execute(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
			throws IOException, ServletException {
		try{
			String userName= request.getParameter("hiddenUserName");
			String passWord = request.getParameter("hiddenPassWord");
//			if(request.getParameter("newUserFlag")!=null && request.getParameter("newUserFlag").equalsIgnoreCase("Y")){
//				System.out.println(request.getParameter("hiddenUserName"));
//				System.out.println(request.getParameter("hiddenPassWord"));
//			}
			//LDAP call
			boolean ldapSuccess = true;
			if(ldapSuccess){
			ResourceUtils resourceUtils = new ResourceUtils();
			ArrayList resourceList = new ArrayList();
			ArrayList teamList = new ArrayList();
			ArrayList itemList = new ArrayList();
			ArrayList dailyActivities = new ArrayList();
			ArrayList statusList = new ArrayList();
			String resourceListVal=resourceUtils.getPropertyValue("RESOURCELIST");
			String teamLeadListVal = resourceUtils.getPropertyValue("TEAMLIST");
			StringTokenizer st = new StringTokenizer(resourceListVal,",");
			while(st.hasMoreElements()){
				resourceList.add(st.nextElement());
			}
			StringTokenizer stLead = new StringTokenizer(teamLeadListVal,",");
			while(stLead.hasMoreElements()){
				teamList.add(stLead.nextElement());
			}
			if(resourceList!=null && resourceList.contains(userName) ){
				String itemValue = resourceUtils.getPropertyValue("ITEMLIST");
				StringTokenizer tokenizeItem = new StringTokenizer(itemValue,",");
				while(tokenizeItem.hasMoreElements()){
					itemList.add(tokenizeItem.nextElement());
				}
				String dailyActivity = resourceUtils.getPropertyValue("DAILYACTIVITIES");
				StringTokenizer tokenizeDaily = new StringTokenizer(dailyActivity,",");
				while (tokenizeDaily.hasMoreElements()){
					dailyActivities.add(tokenizeDaily.nextElement());
				}
				
				String statusActivity = resourceUtils.getPropertyValue("STATUS");
				StringTokenizer tokenizeStatus = new StringTokenizer(statusActivity,",");
				while (tokenizeStatus.hasMoreElements()){
					statusList.add(tokenizeStatus.nextElement());
				}
				HttpSession session = request.getSession();
				session.setAttribute("itemList", itemList);
				session.setAttribute("dailyActivityList", dailyActivities);
				session.setAttribute("status", statusList);
				session.setAttribute("resourceID", userName);
				
				//return mapping.findForward("resourceSucess");
				ArrayList status = new ArrayList();
				status.add("trueUser");
				String jsonResult = new flexjson.JSONSerializer()
	            .serialize(status);
	            response.setContentType("text/javascript");
	            response.getWriter().write(jsonResult);

	            return null;
				
					
			}
			else if(teamList!=null && teamList.contains(userName)){
				return mapping.findForward("teamLeadSuccess");
			}
			else{
				ArrayList status = new ArrayList();
				status.add("notPart");
				String jsonResult = new flexjson.JSONSerializer()
	            .serialize(status);
	            response.setContentType("text/javascript");
	            response.getWriter().write(jsonResult);

	            return null;
			}
			}
			else{
				ArrayList status = new ArrayList();
				status.add("invalid");
				String jsonResult = new flexjson.JSONSerializer()
	            .serialize(status);
	            response.setContentType("text/javascript");
	            response.getWriter().write(jsonResult);

	            return null;
			}
//			if(userName!=null && userName.equalsIgnoreCase("334988") && passWord!=null && passWord.equalsIgnoreCase("hai") ){
//				return mapping.findForward("resourceSucess");
//			}
//			else{
//			ArrayList status = new ArrayList();
//			status.add("newUser");
//			String jsonResult = new flexjson.JSONSerializer()
//            .serialize(status);
//            response.setContentType("text/javascript");
//            response.getWriter().write(jsonResult);
//
//            return null;
//			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}
}
