package com.foly.own.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foly.own.db.OwnMainDAO;
import com.foly.own.db.OwnMainDTO;
import com.foly.util.Action;
import com.foly.util.ActionForward;

public class OwnInfoContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : OwnInfoContentAction ");
		
		// 전달정보 저장 me_num, own_id, pageNum
		String pageNum = request.getParameter("pageNum");
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
		

		

		
		// DAO - 특정글 정보 조회
		OwnMainDAO dao = new OwnMainDAO();		
		OwnMainDTO dto = dao.getOwnRestInfo(own_id);
		
		
		// request 영역에 정보 저장
		request.setAttribute("dto", dto);
		
		
		// 페이지 이동
		forward = new ActionForward();
		forward.setPath("/owner/ownInfoContent.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
