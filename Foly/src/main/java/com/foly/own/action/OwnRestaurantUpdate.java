package com.foly.own.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foly.own.db.OwnMainDAO;
import com.foly.own.db.OwnMainDTO;
import com.foly.util.Action;
import com.foly.util.ActionForward;

public class OwnRestaurantUpdate implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 로그인을 안한 경우 
				HttpSession session = request.getSession();
				String own_id = (String)session.getAttribute("own_id");
					
				ActionForward forward = new ActionForward();
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
				
				String own_pw = request.getParameter("own_pw");
				
				OwnMainDAO dao = new OwnMainDAO();
				int result = dao.ownPwCheck(own_id, own_pw);
				
				if(result == 0) {
					response.setContentType("text/html; charset=utf-8");
					PrintWriter out = response.getWriter();
					out.print("<script>");			
					out.print("alert('비밀번호 틀렸음!');");			
					out.print("history.back();");			
					out.print("</script>");
					out.close();
					
					return null; // JS이동 수행시 컨트롤러 이동X
				}else if(result == 1) {
					
					OwnMainDAO dao1 = new OwnMainDAO();
					OwnMainDTO dto = new OwnMainDTO();
					dto = dao1.getOwnRestInfo(own_id);
					
					
					request.setAttribute("dto", dto);
					
					// 페이지 이동(forward)
					forward = new ActionForward();
					forward.setPath("./owner/ownRestaurantUpdate.jsp");
					forward.setRedirect(false);
			
					return forward;
				}
				
				return forward;	
	}
	
}
