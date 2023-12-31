package com.foly.res.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// import javax.servlet.http.HttpSession;

import com.foly.res.db.ResDAO;
import com.foly.res.db.SearchDTO;
import com.foly.util.Action;
import com.foly.util.ActionForward;


public class Categori implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// HttpSession session = request.getSession();
		String res_type = request.getParameter("res_type");
		System.out.println(res_type);
		
		ResDAO dao = new ResDAO();
		ArrayList<SearchDTO> resSearch = dao.getCateRes(res_type);
		
		for(SearchDTO s : resSearch) {
			System.out.println(s);
		}
		
		request.setAttribute("resSearch", resSearch);
		// session.setAttribute("info", info);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./res/resSearch.jsp");
		forward.setRedirect(false);
		
		return forward;
	
	}
		

}
