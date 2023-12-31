package com.foly.login.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foly.login.db.LoginDAO;
import com.foly.util.Action;
import com.foly.util.ActionForward;

public class CheckEmailAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {


		System.out.println(" M : CheckEmailAction_execute() 호출 ");
		
		LoginDAO dao = new LoginDAO();
		
		int result = dao.CheckEmail(request.getParameter("us_email"));
		 
		
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(result);
		
		return null;
	}

}


