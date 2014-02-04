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
import resource.excel.teamlead.AllotTaskExcel;
import resource.excel.teamlead.AssignTaskExcel;

public class AllotTaskAction extends Action {

	public ActionForward execute(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
			throws IOException, ServletException {
		try{
			
			AllotTaskExcel allotTaskExcel = new AllotTaskExcel();
			ArrayList <AllotTaskDO>  allotTaskDOList= new ArrayList<AllotTaskDO>();
			HttpSession session = request.getSession();
			allotTaskDOList=(ArrayList<AllotTaskDO>) session.getAttribute("allotTaskGridLoad");
			String status = allotTaskExcel.updateAllotTask(allotTaskDOList);
			if(status!=null&&"success".equalsIgnoreCase(status)){
				session.setAttribute("allotTaskGridLoad", null);
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
		return null;
	}
}
