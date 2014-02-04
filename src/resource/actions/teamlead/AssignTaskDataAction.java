package resource.actions.teamlead;

import java.io.IOException;
import java.util.ArrayList;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import resource.dataobject.teamlead.AssignTaskDO;





public class AssignTaskDataAction extends Action{
	
	public ActionForward execute(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
			throws IOException, ServletException {
		try{
			HttpSession session = request.getSession();
			
			
			if(request.getParameter("gridLoadFlag")!=null){
				
			 AssignTaskDO assignTaskDO = new AssignTaskDO();
				
				assignTaskDO.setActivity(request.getParameter("hidenActivity"));
				assignTaskDO.setDate(request.getParameter("hiddenDate"));
				assignTaskDO.setDescription(request.getParameter("hiddenDescription"));
				assignTaskDO.setHoursSpent(request.getParameter("hiddenHourSpent"));
				assignTaskDO.setIsPartofAssignedTask(request.getParameter("hiddenIsPartOfAssignedTask"));
				assignTaskDO.setItem(request.getParameter("hiddenItem"));
 
					ArrayList <AssignTaskDO>  assignTaskDOList=(ArrayList<AssignTaskDO>) session.getAttribute("gridLoad");
					if(assignTaskDOList!=null){
					assignTaskDOList.add(assignTaskDO);
					session.setAttribute("gridLoad",assignTaskDOList );
					}
					else{
						assignTaskDOList=new ArrayList<AssignTaskDO>();
						assignTaskDOList.add(assignTaskDO);
						session.setAttribute("gridLoad",assignTaskDOList );
					}
					
				
					return null;

			}
			
			ArrayList<String> itemList =(ArrayList) session.getAttribute("itemList");
			
			
			request.getSession().setAttribute("itemListKeys", itemList);
			request.getSession().setAttribute("itemListValues", itemList);
			
			
			ArrayList<String> activityList = (ArrayList) session.getAttribute("dailyActivityList");
//			activityList.add("Requirement Analaysis");
//			activityList.add("Estimate");
//			activityList.add("Design");
//			activityList.add("Coding");
//			activityList.add("Unit Testing");
//			activityList.add("Code Review");
//			activityList.add("Documentation");
//			activityList.add("Onsite Call");
			
			
			


			
			request.getSession().setAttribute("activityListKeys", activityList);
			request.getSession().setAttribute("activityListValues", activityList);
			
			ArrayList<String> isPartOfAssigned = (ArrayList) session.getAttribute("status");
//			isPartOfAssigned.add("Yes");
//			isPartOfAssigned.add("No");
//			isPartOfAssigned.add("N/A");
			
			request.getSession().setAttribute("partOfAssignedListKeys", isPartOfAssigned);
			request.getSession().setAttribute("partOfAssignedListValues", isPartOfAssigned);
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}

		return mapping.findForward("success");
	}

}
