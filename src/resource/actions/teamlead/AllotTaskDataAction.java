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

import resource.dataobject.teamlead.AllotTaskDO;
import resource.dataobject.teamlead.AssignTaskDO;

public class AllotTaskDataAction extends Action{
	public ActionForward execute(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
			throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		try{
			
			if(request.getParameter("gridLoadFlag")!=null && "Y".equalsIgnoreCase(request.getParameter("gridLoadFlag"))){
				AllotTaskDO allotTaskDO = new AllotTaskDO();
				allotTaskDO.setResource(request.getParameter("hidenResource"));
				allotTaskDO.setTask(request.getParameter("hiddenTask"));
				
				ArrayList <AllotTaskDO>  allotTaskDOList=(ArrayList<AllotTaskDO>) session.getAttribute("allotTaskGridLoad");
				
				if(allotTaskDOList!=null){
					allotTaskDOList.add(allotTaskDO);
					session.setAttribute("allotTaskGridLoad", allotTaskDOList);
				}
				else {
					allotTaskDOList=new ArrayList<AllotTaskDO>();
					allotTaskDOList.add(allotTaskDO);
					session.setAttribute("allotTaskGridLoad", allotTaskDOList);
				}
				System.out.println(allotTaskDOList);
			}
			
			
			ArrayList resourceList = new ArrayList ();
			resourceList.add("Nerlesh");
			resourceList.add("Navneeth");
			resourceList.add("Siddhartha");
			resourceList.add("Vijay");
			resourceList.add("Murugan");
			resourceList.add("Shobana");
			request.getSession().setAttribute("resourceListKeys", resourceList);
			request.getSession().setAttribute("resourceListValues", resourceList);
			
		}
		catch(Exception e){
			
		}
		return mapping.findForward("success");
	}
}
