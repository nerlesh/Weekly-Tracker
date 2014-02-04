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
import resource.dataobject.teamlead.ProcessStatusDO;
import resource.excel.teamlead.AssignTaskExcel;
import resource.excel.teamlead.ProcessStatusExcel;

public class AssignTaskSubAction extends Action{

	public ActionForward execute(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
			throws IOException, ServletException {
		try{
			
			AssignTaskExcel assignTaskExcel = new AssignTaskExcel();
			ArrayList<AssignTaskDO> assignStatusList = new ArrayList<AssignTaskDO>();
			HttpSession session = request.getSession();
			String assoId=(String)session.getAttribute("resourceID");
			assignStatusList=(ArrayList<AssignTaskDO>) session.getAttribute("gridLoad");
			String status = assignTaskExcel.updateAssignTask(assignStatusList, assoId);
			if(status!=null&&"sucess".equalsIgnoreCase(status)){
				session.setAttribute("gridLoad", null);
				//System.out.println("returning null");
				ArrayList statusList = new ArrayList();
				statusList.add("success");
				String jsonResult = new flexjson.JSONSerializer()
	            .serialize(statusList);
	            response.setContentType("text/javascript");
	            response.getWriter().write(jsonResult);
				return null;
			}
			
		}
		catch(Exception e){
			
		}
		return mapping.findForward("success");
	}
	
	
}
