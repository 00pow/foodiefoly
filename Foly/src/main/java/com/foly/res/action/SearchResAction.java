package com.foly.res.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foly.res.db.ResDAO;
import com.foly.res.db.SearchDTO;
import com.foly.util.Action;
import com.foly.util.ActionForward;

public class SearchResAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String str = request.getParameter("serch");
		System.out.println(str);
		
		if(str.isEmpty()) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");			
			out.print("alert('검색어를 입력하세요!');");			
			out.print("history.back();");			
			out.print("</script>");
			out.close();
			
			return null;
		}
		
		ResDAO dao = new ResDAO();
		ArrayList<SearchDTO> resSearch = dao.serchRes(str);
		
		if(resSearch.isEmpty()) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");			
			out.print("alert('검색결과가 없습니다!');");			
			out.print("location.href='./Main.lo';");			
			out.print("</script>");
			out.close();
			
			return null;
		}
		
		for(SearchDTO d : resSearch) {
			System.out.println(d);
		}
		
		request.setAttribute("resSearch", resSearch);
		// session.setAttribute("info", info);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./res/resSearch.jsp");
		forward.setRedirect(false);
		
		return forward;
		
	}
	
	

}
