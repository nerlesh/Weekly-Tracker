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

import resource.excel.teamlead.ReadAllotedTask;

public class HomeAction extends Action{
	public ActionForward execute(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
			throws IOException, ServletException {
		try{
			System.out.println("im entering"+request.getAttribute("gridLoadFlag"));
			//if(request.getAttribute("gridLoadFlag")!=null && "Y".equalsIgnoreCase(request.getAttribute("gridLoadFlag").toString())){
				System.out.println("im entering");
			ArrayList resultList = new ArrayList();
			ReadAllotedTask readAllotedTask = new ReadAllotedTask();
			HttpSession session = request.getSession();
			session.setAttribute("resourceID", "334988");
			String userID = (String)session.getAttribute("resourceID");
			//Bring Assigned task
			resultList =readAllotedTask.readAllotedExcel(userID);
			
			String jsonResult = new flexjson.JSONSerializer()
            .serialize(resultList);
            response.setContentType("text/javascript");
            response.getWriter().write(jsonResult);

            return null;
			//}
		}
		catch(Exception e){
			
		}
		return mapping.findForward("success");
	}
}
