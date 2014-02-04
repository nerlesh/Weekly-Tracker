package resource.actions.teamlead;

import java.io.IOException;
import java.io.Serializable;
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

public class ProcessStatusDataAction extends Action  {
	
	

	public ActionForward execute(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
			throws IOException, ServletException {
		try{
			System.out.println("enter");
			HttpSession session = request.getSession();
		if(request.getParameter("gridLoadFlag")!=null &&"Y".equalsIgnoreCase(request.getParameter("gridLoadFlag"))){
			ProcessStatusDO processStatusDO = new ProcessStatusDO();
			processStatusDO.setActivity(request.getParameter("hiddenActivity"));
			processStatusDO.setCount(request.getParameter("hiddenCount"));
			processStatusDO.setItems(request.getParameter("hiddenItem"));
			processStatusDO.setStatus(request.getParameter("hiddenStatus"));
			
			
			ArrayList<ProcessStatusDO> processGridList = (ArrayList<ProcessStatusDO>) session.getAttribute("processStatusGrid");
			if(processGridList!=null){
				processGridList.add(processStatusDO);
				session.setAttribute("processStatusGrid", processGridList);
			}
			else{
				processGridList = new ArrayList<ProcessStatusDO>();
				processGridList.add(processStatusDO);
				session.setAttribute("processStatusGrid", processGridList);
			}
		}
		//System.out.println("enter hai");
		ArrayList<String> itemList = (ArrayList) session.getAttribute("itemList");
//		itemList.add("GCB 7803.1");
//		itemList.add("GCB 7803.2");
//		itemList.add("General");
//		itemList.add("SME");
//		itemList.add("IB 7623");
//		itemList.add("IB 7633");
		System.out.println(itemList);
		request.getSession().setAttribute("itemListKeys", itemList);
		request.getSession().setAttribute("itemListValues", itemList);
		
		
		ArrayList<String> processActivityList = new ArrayList<String>();
		processActivityList.add("Requirement Clarification");
		processActivityList.add("Design Clarification");
		processActivityList.add("Design Review");
		processActivityList.add("Code Review");
		processActivityList.add("Defects");
	
		
		
		request.getSession().setAttribute("processActivityListKeys", processActivityList);
		request.getSession().setAttribute("processActivityListValues", processActivityList);
		
		ArrayList<String> status =new ArrayList<String>();
		status.add("Open");
		status.add("Closed");
		status.add("Fixed");
		status.add("Defered");
		status.add("Reopen");
		
		request.getSession().setAttribute("statusListKeys", status);
		request.getSession().setAttribute("statusListValues", status);
		
		
		return mapping.findForward("success");
	}
		catch(Exception e){
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}
}
