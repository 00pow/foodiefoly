package com.foly.login.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foly.login.db.LoginDAO;
import com.foly.util.Action;
import com.foly.util.ActionForward;



public class CheckIdAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {


		System.out.println(" M : CheckIdAction_execute() 호출 ");
		
		LoginDAO dao = new LoginDAO();
		
		int result = dao.CheckId(request.getParameter("us_id"));
		 
		
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(result);
		
		return null;
	}

}

	
	
	
