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
import resource.dataobject.teamlead.LeaveApplyDO;
import resource.dataobject.teamlead.ProcessStatusDO;

public class LeaveApplyDataAction extends Action{
	public ActionForward execute(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
			throws IOException, ServletException {
		try{
			ResourceUtils resourceUtils = new ResourceUtils();
			HttpSession session = request.getSession();
			if(request.getParameter("gridLoadFlag")!=null &&"Y".equalsIgnoreCase(request.getParameter("gridLoadFlag"))){
				LeaveApplyDO leaveApplyDO = new LeaveApplyDO();
				leaveApplyDO.setDate(request.getParameter("hiddenDate"));
				leaveApplyDO.setAppliedPortal(request.getParameter("hiddenLeaveType"));
				leaveApplyDO.setLeaveType(request.getParameter("hiddenAppPortal"));
				
				
				ArrayList<LeaveApplyDO> leaveApplyGridList = (ArrayList<LeaveApplyDO>) session.getAttribute("leaveApplyGrid");
				if(leaveApplyGridList!=null){
					
					leaveApplyGridList.add(leaveApplyDO);
					session.setAttribute("leaveApplyGrid", leaveApplyGridList);
				}
				else{
					leaveApplyGridList = new ArrayList<LeaveApplyDO>();
					leaveApplyGridList.add(leaveApplyDO);
					session.setAttribute("leaveApplyGrid", leaveApplyGridList);
				}
				
				System.out.println("im printing"+leaveApplyGridList);
			}
			String status= resourceUtils.getPropertyValue("STATUS");
			ArrayList statusList = new ArrayList();
			if(status!=null){
			StringTokenizer str = new StringTokenizer(status, ";");
			while(str.hasMoreElements()){
				statusList.add(str.nextElement());
			}
			}
			request.getSession().setAttribute("statusListKeys", statusList);
			request.getSession().setAttribute("statusListValues", statusList);
			
			String leave= resourceUtils.getPropertyValue("LEAVETYPE");
			ArrayList leaveTypeList = new ArrayList();
			if(status!=null){
			StringTokenizer str = new StringTokenizer(leave, ";");
			while(str.hasMoreElements()){
				leaveTypeList.add(str.nextElement());
			}
			}
			request.getSession().setAttribute("activityListKeys", leaveTypeList);
			request.getSession().setAttribute("activityListValues", leaveTypeList);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}
}
