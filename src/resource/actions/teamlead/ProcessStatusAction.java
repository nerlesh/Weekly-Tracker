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

import resource.dataobject.teamlead.ProcessStatusDO;
import resource.excel.teamlead.ProcessStatusExcel;
import resource.forms.teamlead.ProcessStatusForm;

public class ProcessStatusAction extends Action {
	public ActionForward execute(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
			throws IOException, ServletException {
		try{
			
			ProcessStatusExcel processStatusExcel = new ProcessStatusExcel();
			ArrayList <ProcessStatusDO> pStatusList = new ArrayList<ProcessStatusDO>();
			HttpSession session = request.getSession();
			String assoId=(String)session.getAttribute("resourceID");
			pStatusList=(ArrayList<ProcessStatusDO>) session.getAttribute("processStatusGrid");
			String status = processStatusExcel.updateProcessStatus(pStatusList,assoId);
			if(status!=null&&"sucess".equalsIgnoreCase(status)){
				session.setAttribute("processStatusGrid", null);
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
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}
}
