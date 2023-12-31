package com.foly.own.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foly.own.db.OwnMainDAO;
import com.foly.res.db.ResDTO;
import com.foly.util.Action;
import com.foly.util.ActionForward;

public class OwnMenuContent implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : OwnContentAction_execute() 실행 ");
		
		// 세션정보 확인
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
	
		// 전달정보 저장 me_num, own_id, pageNum
		int me_num = Integer.parseInt(request.getParameter("me_num"));
		String pageNum = request.getParameter("pageNum");
		

		
		// DAO - 특정글 정보 조회(me_num)
		OwnMainDAO dao = new OwnMainDAO();		
		ResDTO dto = dao.getMenuContent(own_id,me_num);
		
		
		// request 영역에 정보 저장
		request.setAttribute("dto", dto);
		
		// request.setAttribute("pageNum", pageNum);
		
		// 페이지 이동
		forward = new ActionForward();
		forward.setPath("/owner/ownMenuContent.jsp?pageNum ="+pageNum);
		forward.setRedirect(false);
		
		return forward;
	}

}
