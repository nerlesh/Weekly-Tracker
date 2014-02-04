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

import resource.dataobject.teamlead.LeaveApplyDO;
import resource.dataobject.teamlead.ProcessStatusDO;
import resource.excel.teamlead.LeaveApplyExcel;
import resource.excel.teamlead.ProcessStatusExcel;


public class LeaveApplyAction extends Action{

	public ActionForward execute(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
			throws IOException, ServletException {
		try{
			LeaveApplyExcel leaveApplyExcel = new LeaveApplyExcel();
			ArrayList <LeaveApplyDO> lApplyList = new ArrayList<LeaveApplyDO>();
			HttpSession session = request.getSession();
			String assoId=(String)session.getAttribute("resourceID");
			lApplyList=(ArrayList<LeaveApplyDO>) session.getAttribute("leaveApplyGrid");
			String status = leaveApplyExcel.updateLeaveApply(lApplyList,"334988");
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
