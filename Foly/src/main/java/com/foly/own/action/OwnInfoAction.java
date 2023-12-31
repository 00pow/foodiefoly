package com.foly.own.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foly.own.db.OwnMainDAO;
import com.foly.own.db.OwnMainDTO;
import com.foly.util.Action;
import com.foly.util.ActionForward;

public class OwnInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : OwnInfoAction 호출");
		HttpSession session = request.getSession();
		String own_id = (String)session.getAttribute("own_id");
		if (own_id == null) {
				// 사용자가 보는 화면은 html 형식을 띄게 하면서
				response.setContentType("text/html; charset=UTF-8");
				// 글을 쓸 수 있게 해준다
				PrintWriter out = response.getWriter();
						
				out.println("HTML 코드 사용 가능");
				out.println("<script>");
				out.println("alert('로그인이 필요합니다.');");
				out.println("location.href='./OwnLogin.lo';");
				out.println("</script>");
						
				out.close();
						
				// 컨트롤러의 페이지 이동 막음 
				return null;
		
		}	
		
		
		OwnMainDAO dao = new OwnMainDAO();
		OwnMainDTO dto = dao.getOwnIntoContent(own_id);
		
		request.setAttribute("dto", dto);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./owner/ownInfoContent.jsp");
		forward.setRedirect(false);
		return forward;
		
	}

}
